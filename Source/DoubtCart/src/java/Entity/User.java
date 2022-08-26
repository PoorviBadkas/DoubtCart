/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author HP
 */
@Entity
@Table(catalog = "resources", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u WHERE u.username != 'admin'"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findBySemester", query = "SELECT u FROM User u WHERE u.semester = :semester"),
    @NamedQuery(name = "User.findByProfile", query = "SELECT u FROM User u WHERE u.profile = :profile"),
    @NamedQuery(name = "User.findByOtp", query = "SELECT u FROM User u WHERE u.otp = :otp"),
    @NamedQuery(name = "User.findByPoints", query = "SELECT u FROM User u WHERE u.points >= :points"),
    @NamedQuery(name = "User.findByWarnings", query = "SELECT u FROM User u WHERE u.warnings = :warnings"),
    @NamedQuery(name = "User.findByIsBlocked", query = "SELECT u FROM User u WHERE u.isBlocked = :isBlocked")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(nullable = false, length = 500)
    private String username;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(nullable = false, length = 2000)
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5000)
    @Column(nullable = false, length = 5000)
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int semester;
    @Size(max = 1000)
    @Column(length = 1000)
    private String profile;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int otp;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int points;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int warnings;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean isBlocked;
    @ManyToMany(mappedBy = "userCollection")
    private Collection<Badge> badgeCollection;
    @ManyToMany(mappedBy = "userCollection")
    private Collection<Groups> groupsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private Collection<Liketb> liketbCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private Collection<Comments> commentsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "username")
    private Collection<Answer> answerCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "username")
    private Collection<Doubt> doubtCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private Collection<Doubtcomment> doubtcommentCollection;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public User(String username, String email, String password, int semester, int otp, int points, int warnings, boolean isBlocked) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.semester = semester;
        this.otp = otp;
        this.points = points;
        this.warnings = warnings;
        this.isBlocked = isBlocked;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getWarnings() {
        return warnings;
    }

    public void setWarnings(int warnings) {
        this.warnings = warnings;
    }

    public boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    @JsonbTransient
    @XmlTransient
    public Collection<Badge> getBadgeCollection() {
        return badgeCollection;
    }

    public void setBadgeCollection(Collection<Badge> badgeCollection) {
        this.badgeCollection = badgeCollection;
    }

    @JsonbTransient
    @XmlTransient
    public Collection<Groups> getGroupsCollection() {
        return groupsCollection;
    }

    public void setGroupsCollection(Collection<Groups> groupsCollection) {
        this.groupsCollection = groupsCollection;
    }

    @JsonbTransient
    @XmlTransient
    public Collection<Liketb> getLiketbCollection() {
        return liketbCollection;
    }

    public void setLiketbCollection(Collection<Liketb> liketbCollection) {
        this.liketbCollection = liketbCollection;
    }

    @JsonbTransient
    @XmlTransient
    public Collection<Comments> getCommentsCollection() {
        return commentsCollection;
    }

    public void setCommentsCollection(Collection<Comments> commentsCollection) {
        this.commentsCollection = commentsCollection;
    }

    @JsonbTransient
    @XmlTransient
    public Collection<Answer> getAnswerCollection() {
        return answerCollection;
    }

    public void setAnswerCollection(Collection<Answer> answerCollection) {
        this.answerCollection = answerCollection;
    }

    @JsonbTransient
    @XmlTransient
    public Collection<Doubt> getDoubtCollection() {
        return doubtCollection;
    }

    public void setDoubtCollection(Collection<Doubt> doubtCollection) {
        this.doubtCollection = doubtCollection;
    }

    @JsonbTransient
    @XmlTransient
    public Collection<Doubtcomment> getDoubtcommentCollection() {
        return doubtcommentCollection;
    }

    public void setDoubtcommentCollection(Collection<Doubtcomment> doubtcommentCollection) {
        this.doubtcommentCollection = doubtcommentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.User[ username=" + username + " ]";
    }
    
}
