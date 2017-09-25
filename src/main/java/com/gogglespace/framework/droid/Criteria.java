package com.gogglespace.framework.droid;

import java.util.ArrayList;
import java.util.List;

/**
 * Droid Persistence Framework
 * Persistence framework - criteria
 * Created by Shahid Nawaz on 4/20/2015.
 * @version 1.0
 *
 */
public class Criteria {

    List<Restriction> restrictions;
    List<String> orderByFields;
    OrderBy orderBy;

    public void add(Restriction restriction) {
        if (restriction == null) {
        	return;
        }
        if (this.restrictions == null) {
            this.restrictions = new ArrayList<>();
        }

        restrictions.add(restriction);

    }

    public void addOrder(String fieldName) {
        if (this.orderByFields == null) {
            this.orderByFields = new ArrayList<>();
        }
        orderByFields.add(fieldName);
    }

    public void orderBy(OrderBy orderBy) {
        this.orderBy = orderBy;
    }


    /**
     * Order by enumeration
     * */
    public enum OrderBy {
        ASC, DESC
    }

    public List<Restriction> getRestrictions() {
        return restrictions;
    }

    public OrderBy getOrderBy() {
        return orderBy;
    }

    public List<String> getOrderByFields() {
        return orderByFields;
    }

}

