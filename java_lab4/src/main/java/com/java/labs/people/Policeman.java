package com.java.labs.people;

import com.java.labs.departments.Department;

import java.util.Objects;

public class Policeman extends Human {

    private final Department department;

    public Policeman() {
        super();
        this.department = Department.POLICE_DEPARTMENT;
    }

    public Policeman(Policeman policeman) {
        super(policeman);
        this.department = policeman.getDepartment();
    }

    public Department getDepartment() {
        return department;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Policeman policeman)) return false;
        if (!super.equals(o)) return false;
        return department == policeman.department;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), department);
    }
}
