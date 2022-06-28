package com.timothyborghouts.shareameal.logic;

import com.timothyborghouts.shareameal.domain.Meal;

public interface DatasetListener {

    public void addMeal(Meal meal);

    public void datasetUpdated();

}
