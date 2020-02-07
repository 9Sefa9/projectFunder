package de.unidue.inf.is.domain;

public final class User {

    private String name;
    private String email;
    private int projectsCount;
    private int supportedCount;


    public User() {
    }


    public User(String firstname, String email, int projectsCount, int supportedCount) {
        this.name = firstname;
        this.email = email;
        this.projectsCount = projectsCount;
        this.supportedCount = supportedCount;
    }


    public String getFirstname() {
        return name;
    }




	public int getProjectsCount() {
		return projectsCount;
	}


	public void setProjectsCount(int projectsCount) {
		this.projectsCount = projectsCount;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getSupportedCount() {
		return supportedCount;
	}


	public void setSupportedCount(int supportedCount) {
		this.supportedCount = supportedCount;
	}

}