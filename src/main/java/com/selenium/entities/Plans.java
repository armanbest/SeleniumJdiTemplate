package com.selenium.entities;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Plans extends ForwardingSet<Plan> {
    private Set<Plan> delegate;

    public Plans(Plans plans) {
        this.delegate = new HashSet<>(plans.delegate);
    }

    public Plans() {
        this.delegate = new HashSet<>();
    }

    public Plans(Collection<Plan> plans) {
        this.delegate = new HashSet<>(plans);
    }

    @Override
    protected Set<Plan> delegate() {
        return delegate;
    }

    public Plan getAnnualPlan(String name) {
        for (Plan plan : delegate) {
            if (plan.getName().equals(name)) {
                return plan;
            }
        }
        throw new NullPointerException("Undefined Annual Plan Name: " + name);
    }

    public Set<Plan> get() {
        return delegate;
    }
}
