package group1.tutoringcenter.models;



public class Tutors {
    private int id;
    private String name;
    private String email;
    private int experience;
    private String bio;
   


    private String subject;



    public Tutors() {
    }


    public Tutors(String name, String subject) {
        this.name = name;
        this.subject = subject;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getExperience() { return this.experience; }
    public void setExperience(int experience) { this.experience = experience; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
}