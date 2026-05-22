package com.sistemapracticasprofesional.logic.dto;

public class InternDto {
    private String studentId;
    private Integer userId;
    private int age;
    private String name;
    private String indigenousLanguage;
    private String gender;
    private String major;

    public InternDto(){
        
    }

    public InternDto(String studentId, int age, String name, String indigenousLanguage,
                     String gender, String major) {
        this.studentId = studentId;
        this.age = age;
        this.name = name;
        this.indigenousLanguage = indigenousLanguage;
        this.gender = gender;
        this.major = major;
    }

    public InternDto(String studentId, Integer userId, int age, String name, String indigenousLanguage,
                     String gender, String major) {
        this.studentId = studentId;
        this.userId = userId;
        this.age = age;
        this.name = name;
        this.indigenousLanguage = indigenousLanguage;
        this.gender = gender;
        this.major = major;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndigenousLanguage() {
        return indigenousLanguage;
    }

    public void setIndigenousLanguage(String indigenousLanguage) {
        this.indigenousLanguage = indigenousLanguage;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String toString(){
        return name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((studentId == null) ? 0 : studentId.hashCode());
        result = prime * result + age;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((indigenousLanguage == null) ? 0 : indigenousLanguage.hashCode());
        result = prime * result + ((gender == null) ? 0 : gender.hashCode());
        result = prime * result + ((major == null) ? 0 : major.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        InternDto other = (InternDto) obj;
        if (studentId == null) {
            if (other.studentId != null)
                return false;
        } else if (!studentId.equals(other.studentId))
            return false;
        if (age != other.age)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (indigenousLanguage == null) {
            if (other.indigenousLanguage != null)
                return false;
        }
        if (gender == null) {
            if (other.gender != null)
                return false;
        } else if (!gender.equals(other.gender))
            return false;
        if (major == null) {
            if (other.major != null)
                return false;
        } else if (!major.equals(other.major))
            return false;
        return true;
    }
    
    
}
