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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @NamedQuery(name = "Resource.findAll", query = "SELECT r FROM Resource r"),
    @NamedQuery(name = "Resource.findByResourceID", query = "SELECT r FROM Resource r WHERE r.resourceID = :resourceID"),
    @NamedQuery(name = "Resource.findByTitle", query = "SELECT r FROM Resource r WHERE r.title = :title"),
    @NamedQuery(name = "Resource.findByDescription", query = "SELECT r FROM Resource r WHERE r.description = :description"),
    @NamedQuery(name = "Resource.findByTitleAndSubject", query = "SELECT r FROM Resource r WHERE r.title LIKE :key OR r.subject LIKE :key"),    
    @NamedQuery(name = "Resource.findBySemester", query = "SELECT r FROM Resource r WHERE r.semester = :semester"),
    @NamedQuery(name = "Resource.findBySubject", query = "SELECT r FROM Resource r WHERE r.subject = :subject"),
    @NamedQuery(name = "Resource.findByImage", query = "SELECT r FROM Resource r WHERE r.image = :image")})
public class Resource implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer resourceID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(nullable = false, length = 2000)
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4000)
    @Column(nullable = false, length = 4000)
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int semester;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(nullable = false, length = 1000)
    private String subject;
    @Size(max = 1000)
    @Column(length = 1000)
    private String image;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "resourceID")
    private Collection<Liketb> liketbCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "resourceID")
    private Collection<Comments> commentsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "resourceID")
    private Collection<ResourceFiles> resourceFilesCollection;

    public Resource() {
    }

    public Resource(Integer resourceID) {
        this.resourceID = resourceID;
    }

    public Resource(String title, String description, int semester, String subject) {
        this.title = title;
        this.description = description;
        this.semester = semester;
        this.subject = subject;
    }

    public Integer getResourceID() {
        return resourceID;
    }

    public void setResourceID(Integer resourceID) {
        this.resourceID = resourceID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
    public Collection<ResourceFiles> getResourceFilesCollection() {
        return resourceFilesCollection;
    }

    public void setResourceFilesCollection(Collection<ResourceFiles> resourceFilesCollection) {
        this.resourceFilesCollection = resourceFilesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (resourceID != null ? resourceID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resource)) {
            return false;
        }
        Resource other = (Resource) object;
        if ((this.resourceID == null && other.resourceID != null) || (this.resourceID != null && !this.resourceID.equals(other.resourceID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Resource[ resourceID=" + resourceID + " ]";
    }
    
}
