/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Bean.AdminBeanLocal;
import Bean.DoubtBeanLocal;
import Bean.ResourceBeanLocal;
import Bean.UserBeanLocal;
import Entity.Answer;
import Entity.Category;
import Entity.Comments;
import Entity.Doubt;
import Entity.Resource;
import Entity.ResourceFiles;
import Entity.Tags;
import Entity.User;
import client.AdminClient;
import client.ResourceClient;
import client.UserClient;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Collection;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.servlet.http.Part;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import org.primefaces.shaded.commons.io.FilenameUtils;
import record.KeepRecord;

/**
 *
 * @author HP
 */
@Named(value = "resourceController")
@ApplicationScoped
public class ResourceController {

    @EJB ResourceBeanLocal rbl;
    @EJB UserBeanLocal ubl;
    @EJB DoubtBeanLocal dbl;
    @EJB AdminBeanLocal abl;
    
    ResourceClient cl = new ResourceClient();
    UserClient usercl = new UserClient();
    AdminClient admincl = new AdminClient();
    Response res;
    Collection<Comments> CommentList;
    GenericType<Collection<Comments>> gCommentList = new GenericType<Collection<Comments>>() {};
    Collection<Resource> resList;
    GenericType<Collection<Resource>> gresList = new GenericType<Collection<Resource>>() {};
    Collection<Doubt> doubtlist;
    GenericType<Collection<Doubt>> gdoubtlist = new GenericType<Collection<Doubt>>() {};
    Collection<Answer> anslist;
    GenericType<Collection<Answer>> ganslist = new GenericType<Collection<Answer>>() {};
    Collection<ResourceFiles> resfList;
    GenericType<Collection<ResourceFiles>> gresfList = new GenericType<Collection<ResourceFiles>>() {};
    Integer cnt,UserID,ResourceID,ID,Semester,point;
    String Comment,Title,Description,subject,searchKey,Name,Email,Password,OTP,username,oldPassword,newPassword,successmsg,failmsg,grfile,profilemsg;
    GenericType<Resource> gresource = new GenericType<Resource> () {};
    GenericType<User> guser = new GenericType<User> () {};
    GenericType<Boolean> gboolean = new GenericType<Boolean> () {};
    GenericType<Integer> gint = new GenericType<Integer> () {};
    Resource resource;
    Doubt doubt;
    Answer answer;
    User user;
    Comments Comments;
    Part file,res1,res2,res3;
    Collection<Category> catlist;
    Integer catslist[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,00,0,0,00,0,0,0,0,0,0,0,00,0,0,0,0,0,0000,0,00,0,0,0};  
    Integer tagslist[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,00,0,0,00,0,0,0,0,0,0,0,00,0,0,0,0,0,0000,0,00,0,0,0};  
    Collection<Tags> taglist;
    User ProfileUser,Userdetails;
    
    
    @PostConstruct
    public void init()
    {
//        res = usercl.UserInfo(Response.class,KeepRecord.getUsername());
//        ProfileUser = res.readEntity(guser);
        System.out.println("@@@@@@@@@@@@@@@@@@@@"+KeepRecord.getUsername());
            ProfileUser = ubl.UserInfo(KeepRecord.getUsername());
            catlist = dbl.ListCategory();
            taglist = dbl.ListTags();
    }
    /**
     * Creates a new instance of ResourceController
     */
    public ResourceController() {
        //user Profile
    }
    
    public String CallASkDoubt()
    {
        return "/Userside/CreateDoubt.xhtml?faces-redirect=true";
    }
    
    public Collection<User> AllUsers() {
        return  abl.AllUsers();
    }
    public Integer CountAllComments() {
        return rbl.CountAllComments();
    }

    public Integer CountAllFiles() {
        return rbl.CountAllFiles();
    }
    public Integer CountAllUsers() {
        return ubl.CountAllUsers();
    }
    
    public Integer CountAllUsersGTpoints(Integer Points) {
        return ubl.CountAllUsersGTpoints(Points);
    }

    public Integer CountActiveUsers() {
        return ubl.CountActiveUsers();
    }
    public String DoubtClosed(Integer AnswerId) {
        dbl.DoubtClosed(AnswerId);
        return "/Userside/ViewDoubt.xhtml?faces-redirect=true";
    }
    public Collection<Doubt> MyDoubts() {
        return dbl.MyDoubts();
    }
    public Collection<Answer> DoubtAnswers(Doubt d){
        return dbl.SingleDoubt(d.getId()).getAnswerCollection();
    }
    public Collection<Doubt> SolvedDoubts() {
        return dbl.SolvedDoubts();
    }
    
    public Collection<Doubt> UnsolvedDoubt() {
        return dbl.UnsolvedDoubt();
    }
    
    public Collection<Answer> usefulAnswers() {
        return dbl.usefulAnswers();
    }
    public Collection<Answer> MyAnswers() {
        return dbl.MyAsnwers();
    }
    
    
    public String BlockUser(String username) {
       abl.BlockUser(username);
       return "/Adminside/UserList.xhtml?faces-redirect=true";
    }
    
    public String UnBlockUser(String username) {
       abl.UnBlockUser(username);
       return "/Adminside/UserList.xhtml?faces-redirect=true";
    }
    public String DeleteUser(String username) {
        abl.DeleteUser(username);
       return "/Adminside/UserList.xhtml?faces-redirect=true";
    }

    public String UserDetails(String Username) {
        Userdetails = ubl.UserInfo(Username);
        return "/Userside/UserDetails.xhtml?faces-redirect=true";
    }
    
    public String CreateDoubt(String title, String description, Integer Point) {
        dbl.CreateDoubt(title, description, Point);
        this.Description="";
        return "/Userside/MyDoubts.xhtml?faces-redirect=true";
    }

    public String CreateAnswer(Integer DoubtId,String description) {
            dbl.CreateAnswer(DoubtId, KeepRecord.getUsername(), description);
            doubt = dbl.SingleDoubt(DoubtId);
            this.Description ="";
            return "/Userside/ViewDoubt.xhtml?faces-redirect=true";
    }
            
            
     public String CreateResource()
     {
         return "/Adminside/CreateResource.xhtml?faces-redirect=true";
     }
     
    public Collection<ResourceFiles> FilesByResource(Integer ResourceID) {
          res = cl.FilesByResource(Response.class,String.valueOf(ResourceID));
          return res.readEntity(gresfList);
    }
     
     public void SaveComment(Integer ResourceId,String Comment,String username)
     {
         cl.CommentResource(String.valueOf(ResourceId), username,Comment);
         this.Comment = "";
     }
     
     public Integer CountComments(Integer ResourceId)
     {
         res = cl.CountComments(Response.class, String.valueOf(ResourceId));
         return res.readEntity(gint);
     }
     
     public Collection<Comments> CommentsByResource(Integer ResourceId)
     {
//         res = cl.CommentsByResource(Response.class, String.valueOf(ResourceId));
//         return res.readEntity(gCommentList);
         return rbl.CommentsByResource(ResourceId);
     }
      
     public String ReadMore(Resource ress)
     {
         resource = ress;
         return "/Userside/ViewResource.xhtml?faces-redirect=true";
     }
     
     public String ReadMoreDoubt(Doubt d)
     {
         doubt = d;
         return "/Userside/ViewDoubt.xhtml?faces-redirect=true";
     }
        
     public String MyDoubtView(Doubt d)
     {
         doubt = d;
         return "/Userside/ViewDoubt.xhtml?faces-redirect=true";
     }
     
      public String ReadMoreAnswer(Answer a)
     {
         answer = a;
         return "/Userside/ViewAnswer.xhtml?faces-redirect=true";
     }

     public String SaveProfile() {
        try{
            InputStream input = file.getInputStream();
            String path = "F:\\DoubtCart\\web\\FileAssets\\Profile";
            Random random = new Random();
            StringBuilder sb = new StringBuilder();

            sb.append(random.nextInt(9) + 1);
            for (int i = 0; i < 11; i++) {
                sb.append(random.nextInt(10));
            }
            String temp = sb.toString();

            String ext = FilenameUtils.getExtension(file.getSubmittedFileName());
            if(!ext.equalsIgnoreCase("jpg") && !ext.equalsIgnoreCase("jpeg") && !ext.equalsIgnoreCase("png"))
            {
                profilemsg = "Not Valid Image file !!";
                return "/Profile.xhtml?faces-redirect=true";
            }
            
            grfile = temp + KeepRecord.getUsername()+ "."+ext;
            Files.copy(input, new File(path, grfile).toPath());
            
            cl.SaveProfile(KeepRecord.getUsername(), grfile);
            ProfileUser = ubl.UserInfo(KeepRecord.getUsername());
            profilemsg = "Profile Updated !!";
            return "/Profile.xhtml?faces-redirect=true";
        }catch(Exception e){
             profilemsg = "Something Went Wrong !!";
            return "/Profile.xhtml?faces-redirect=true";
            }
     }
    
     
      
     public String changePassword(String username,String oldpassword, String newpassword)
     {
//        res = usercl.ChangePassword(Response.class,username,oldpassword,newpassword);
//        Boolean result = res.readEntity(gboolean);         
         Boolean result =ubl.ChangePassword(oldpassword, newpassword);
        if(result){
           successmsg = "Password Changed Successfully!!";
           return "/Profile.xhtml?faces-redirect=true";
        }
        else{
          failmsg = "ChangePassword Failled !!";
           return "/Profile.xhtml?faces-redirect=true";
        }
     }
     
     public String UpdateDoubt(Doubt d)
     {
         doubt = dbl.SingleDoubt(d.getId());
         cnt=0;
         doubt.getCategoryCollection().forEach(b -> {
                catslist[cnt] = b.getId();
                cnt++;
                    System.out.println("%%%%%%%%%%%%%% cat "+b.getName());

            });
         cnt = 0;
         doubt.getTagsCollection().forEach(b -> {
                tagslist[cnt] = b.getId();
                cnt++;
                    System.out.println("%%%%%%%%%%%%%% tag"+b.getName());

            });        
         
         return "/Userside/UpdateDoubt.xhtml?faces-redirect=true";
     }
     
     public String UpdateAnswer(Answer a)
     {
         answer = a;
         return "/Userside/UpdateAnswer.xhtml?faces-redirect=true";
     }
      
     public String UpdateResource(Resource ress)
     {
         resource = ress;
         return "/Adminside/UpdateResource.xhtml?faces-redirect=true";
     }
     
     public String SaveResource(String title,String description,Integer semester,String Subject,Part rfile)
     {
         try{
            InputStream input = rfile.getInputStream();
            String path = "F:\\DoubtCart\\web\\FileAssets\\ResourceImg";
            Random random = new Random();
            StringBuilder sb = new StringBuilder();

            sb.append(random.nextInt(9) + 1);
            for (int i = 0; i < 11; i++) {
                sb.append(random.nextInt(10));
            }
            String temp = sb.toString();

            String ext = FilenameUtils.getExtension(rfile.getSubmittedFileName());
            if(!ext.equalsIgnoreCase("jpg") && !ext.equalsIgnoreCase("jpeg") && !ext.equalsIgnoreCase("png"))            
            {
                failmsg = "Not valid Image file ";
                return "/Adminside/CreateResource.xhtml?faces-redirect=true";
            }
           
            
            grfile = temp + "_resource."+ext;
            Files.copy(input, new File(path, grfile).toPath());
            
            
            admincl.createResources(String.valueOf(title), String.valueOf(description), String.valueOf(semester), String.valueOf(Subject),grfile);
            this.Description ="";
            return "/Userside/ShowResources.xhtml?faces-redirect=true";
           }catch(Exception e){
            return "/Adminside/CreateResource.xhtml?faces-redirect=true";
            }
                
     }
     
    public String SaveResourceFiles(Part file) {    
        try{
            InputStream input = file.getInputStream();
            String path = "F:\\DoubtCart\\web\\FileAssets\\ResourceFiles";
            Random random = new Random();
            StringBuilder sb = new StringBuilder();

            sb.append(random.nextInt(9) + 1);
            for (int i = 0; i < 11; i++) {
                sb.append(random.nextInt(10));
            }
            String temp = sb.toString();

            String ext = FilenameUtils.getExtension(file.getSubmittedFileName());
            
            grfile = temp + "_resource_files."+ext;
            Files.copy(input, new File(path, grfile).toPath());
            
            
            admincl.SaveResourceFiles(String.valueOf(resource.getResourceID()),grfile);
            return "/Userside/ShowResources.xhtml?faces-redirect=true";
           }catch(Exception e){
               e.printStackTrace();
            return "/Adminside/UpdateResource.xhtml?faces-redirect=true";
            }
         
    }
    
    public String EditDoubt(Integer[] categorydata, Integer[] tagsdata)
    {
        dbl.UpdateDoubt(doubt.getId(),doubt.getTitle() , doubt.getDescription(), doubt.getPoint(), categorydata, tagsdata);
        for (int i =0; i<catslist.length;i++)
        {
            catslist[i] =0;
        }
        for (int i =0; i<tagslist.length;i++)
        {
            tagslist[i]=0;
        }
        
        return "/Userside/MyDoubts.xhtml?faces-redirect=true";
        
    }
    
    public String EditAnswer()
    {
        dbl.UpdateAnswer(answer.getId(),answer.getDescription());
        return "/Userside/ViewDoubt.xhtml?faces-redirect=true";
    }
     
     
     public String EditResource(Part rfile)
     {
         try{
            InputStream input = rfile.getInputStream();
            String path = "F:\\DoubtCart\\web\\FileAssets\\ResourceImg";
            Random random = new Random();
            StringBuilder sb = new StringBuilder();

            sb.append(random.nextInt(9) + 1);
            for (int i = 0; i < 11; i++) {
                sb.append(random.nextInt(10));
            }
            String temp = sb.toString();

            String ext = FilenameUtils.getExtension(rfile.getSubmittedFileName());
            if(!ext.equalsIgnoreCase("jpg") && !ext.equalsIgnoreCase("jpeg") && !ext.equalsIgnoreCase("png"))            
            {
                failmsg = "Not valid Image file ";
                return "/Adminside/UpdateResource.xhtml?faces-redirect=true";
            }
            
            grfile = temp + "_resource."+ext;
            Files.copy(input, new File(path, grfile).toPath());
            //rbl.updateResources(resource.getResourceID(), resource.getTitle(), resource.getDescription(), resource.getSemester(), resource.getSubject(), grfile);
            admincl.updateResources(String.valueOf(resource.getResourceID()),String.valueOf(resource.getTitle()), String.valueOf(resource.getDescription()), String.valueOf(resource.getSemester()), String.valueOf(resource.getSubject()),grfile);
                    

            //FacesContext.getCurrentInstance().getExternalContext().redirect("ShowResources.xhtml");
            return "/Adminside/ResourceList.xhtml?faces-redirect=true";
           }catch(IOException e){
               e.printStackTrace();
                return "/Adminside/UpdateResource.xhtml?faces-redirect=true";
            }
         
     }
     
    public String DeleteAnswer(Integer Id) {
        dbl.DeleteAnswer(Id);
        return "/Userside/ViewDoubt.xhtml?faces-redirect=true";
    }
     public String DeleteDoubt(Integer DoubtID)
     {
         dbl.DeleteDoubt(DoubtID);
         return "/Userside/MyDoubts.xhtml?faces-redirect=true";
     } 
      
     
     public String DeleteDoubtAdmin(Integer DoubtID)
     {
         dbl.DeleteDoubt(DoubtID);
         return "/Adminside/DoubtList.xhtml?faces-redirect=true";
     } 
     
     
     public String DeleteResource(Integer resID)
     {
         admincl.deleteResources(String.valueOf(resID));
         return "/Userside/ResourceList.xhtml?faces-redirect=true";
     } 
     
     public String DeleteComment(Integer CommentID) {
        cl.DeleteComment(String.valueOf(CommentID));
        
        return "/Userside/ViewResource.xhtml?faces-redirect=true";
    }
     public Collection<Resource> ListResource()
     {
        res = cl.ListResources(Response.class);
        return res.readEntity(gresList);
     }
     
     
     public Collection<Doubt> ListDoubt()
     {
        return dbl.ListDoubts();
     }
     
     
     public Collection<Resource> ResourcesBySubject(String Subject) {
         res = cl.ResourcesBySubject(Response.class, Subject);
         return res.readEntity(gresList);
     }
     
     public String CallSearchResource(String key)
     {
         this.searchKey = key;
         return "/Userside/SearchResource.xhtml?faces-redirect=true";
     }
     
     public String CallSearchDoubt(String key)
     {
         this.searchKey = key;
         return "/Userside/SearchDoubt.xhtml?faces-redirect=true";
     }
     
     public Collection<Resource> SearchResources()
     {
        res = cl.SearchByTitleandSubject(Response.class,this.searchKey);
        return res.readEntity(gresList);
     }
     
     public Collection<Doubt> SearchDoubts()
     {
        return dbl.SearchDoubtByTitle(searchKey);
     }

    public UserClient getUsercl() {
        return usercl;
    }

    public void setUsercl(UserClient usercl) {
        this.usercl = usercl;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Part getRes1() {
        return res1;
    }

    public void setRes1(Part res1) {
        this.res1 = res1;
    }

    public Part getRes2() {
        return res2;
    }

    public void setRes2(Part res2) {
        this.res2 = res2;
    }

    public Part getRes3() {
        return res3;
    }

    public void setRes3(Part res3) {
        this.res3 = res3;
    }

    public GenericType<Boolean> getGboolean() {
        return gboolean;
    }

    public void setGboolean(GenericType<Boolean> gboolean) {
        this.gboolean = gboolean;
    }

    public Collection<Comments> getCommentList() {
        return CommentList;
    }

    public void setCommentList(Collection<Comments> CommentList) {
        this.CommentList = CommentList;
    }

    public GenericType<Collection<Comments>> getgCommentList() {
        return gCommentList;
    }

    public void setgCommentList(GenericType<Collection<Comments>> gCommentList) {
        this.gCommentList = gCommentList;
    }

    public String getSuccessmsg() {
        return successmsg;
    }

    public void setSuccessmsg(String successmsg) {
        this.successmsg = successmsg;
    }

    public GenericType<User> getGuser() {
        return guser;
    }

    public void setGuser(GenericType<User> guser) {
        this.guser = guser;
    }

    public User getProfileUser() {
        return ProfileUser;
    }

    public void setProfileUser(User ProfileUser) {
        this.ProfileUser = ProfileUser;
    }

    public AdminClient getAdmincl() {
        return admincl;
    }

    public void setAdmincl(AdminClient admincl) {
        this.admincl = admincl;
    }

    public User getUserdetails() {
        return Userdetails;
    }

    public void setUserdetails(User Userdetails) {
        this.Userdetails = Userdetails;
    }

     
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getGrfile() {
        return grfile;
    }

    public void setGrfile(String grfile) {
        this.grfile = grfile;
    }

    public GenericType<Integer> getGint() {
        return gint;
    }

    public void setGint(GenericType<Integer> gint) {
        this.gint = gint;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public UserBeanLocal getUbl() {
        return ubl;
    }

    public void setUbl(UserBeanLocal ubl) {
        this.ubl = ubl;
    }

    public Collection<Doubt> getDoubtlist() {
        return doubtlist;
    }

    public void setDoubtlist(Collection<Doubt> doubtlist) {
        this.doubtlist = doubtlist;
    }

    public GenericType<Collection<Doubt>> getGdoubtlist() {
        return gdoubtlist;
    }

    public void setGdoubtlist(GenericType<Collection<Doubt>> gdoubtlist) {
        this.gdoubtlist = gdoubtlist;
    }

    public Integer[] getCatslist() {
        return catslist;
    }

    public void setCatslist(Integer[] catslist) {
        this.catslist = catslist;
    }

    public Integer[] getTagslist() {
        return tagslist;
    }

    public void setTagslist(Integer[] tagslist) {
        this.tagslist = tagslist;
    }

    
    public Collection<Answer> getAnslist() {
        return anslist;
    }

    public void setAnslist(Collection<Answer> anslist) {
        this.anslist = anslist;
    }

    public GenericType<Collection<Answer>> getGanslist() {
        return ganslist;
    }

    public void setGanslist(GenericType<Collection<Answer>> ganslist) {
        this.ganslist = ganslist;
    }

    public String getFailmsg() {
        return failmsg;
    }

    public void setFailmsg(String failmsg) {
        this.failmsg = failmsg;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOTP() {
        return OTP;
    }

    public void setOTP(String OTP) {
        this.OTP = OTP;
    }

    public DoubtBeanLocal getDbl() {
        return dbl;
    }

    public void setDbl(DoubtBeanLocal dbl) {
        this.dbl = dbl;
    }

    public Collection<ResourceFiles> getResfList() {
        return resfList;
    }

    public void setResfList(Collection<ResourceFiles> resfList) {
        this.resfList = resfList;
    }

    public GenericType<Collection<ResourceFiles>> getGresfList() {
        return gresfList;
    }

    public void setGresfList(GenericType<Collection<ResourceFiles>> gresfList) {
        this.gresfList = gresfList;
    }

    public Doubt getDoubt() {
        return doubt;
    }

    public void setDoubt(Doubt doubt) {
        this.doubt = doubt;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Collection<Category> getCatlist() {
        return catlist;
    }

    public void setCatlist(Collection<Category> catlist) {
        this.catlist = catlist;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public Collection<Tags> getTaglist() {
        return taglist;
    }

    public void setTaglist(Collection<Tags> taglist) {
        this.taglist = taglist;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
     

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Comments getComments() {
        return Comments;
    }

    public void setComments(Comments Comments) {
        this.Comments = Comments;
    }

    public ResourceBeanLocal getRbl() {
        return rbl;
    }

    public void setRbl(ResourceBeanLocal rbl) {
        this.rbl = rbl;
    }

    public GenericType<Resource> getGresource() {
        return gresource;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public void setGresource(GenericType<Resource> gresource) {
        this.gresource = gresource;
    }

     
    public ResourceClient getCl() {
        return cl;
    }

    public void setCl(ResourceClient cl) {
        this.cl = cl;
    }

    public Response getRes() {
        return res;
    }

    public void setRes(Response res) {
        this.res = res;
    }

    public Collection<Resource> getResList() {
        return resList;
    }

    public void setResList(Collection<Resource> resList) {
        this.resList = resList;
    }

    public GenericType<Collection<Resource>> getGresList() {
        return gresList;
    }

    public void setGresList(GenericType<Collection<Resource>> gresList) {
        this.gresList = gresList;
    }

    public String getProfilemsg() {
        return profilemsg;
    }

    public void setProfilemsg(String profilemsg) {
        this.profilemsg = profilemsg;
    }

    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer UserID) {
        this.UserID = UserID;
    }

    public Integer getResourceID() {
        return ResourceID;
    }

    public void setResourceID(Integer ResourceID) {
        this.ResourceID = ResourceID;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getSemester() {
        return Semester;
    }

    public void setSemester(Integer Semester) {
        this.Semester = Semester;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
    
    
    
}
