package com.java.labs.people;

import com.java.labs.departments.Department;

import java.util.Objects;

public class Firefighter extends Human {

    private final Department department;

    public Firefighter() {
        super();
        this.department = Department.FIRE_DEPARTMENT;
    }

    public Firefighter(Firefighter firefighter) {
        super(firefighter);
        this.department = firefighter.getDepartment();
    }

    public Department getDepartment() {
        return department;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Firefighter that)) return false;
        if (!super.equals(o)) return false;
        return department == that.department;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), department);
    }
}
