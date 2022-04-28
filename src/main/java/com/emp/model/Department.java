package com.emp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Department implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String dept_name;
    @OneToMany(
            mappedBy = "department",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<Project> projects;
    

        public Department() {
        }


		public String getDept_name() {
			return dept_name;
		}


		public void setDept_name(String dept_name) {
			this.dept_name = dept_name;
		}


		public String getId() {
			return id;
		}


		public void setId(String id) {
			this.id = id;
		}


		public List<Project> getProjects() {
			return projects;
		}


		public void setProjects(List<Project> projects) {
			this.projects = projects;
		}
		
    
    
}