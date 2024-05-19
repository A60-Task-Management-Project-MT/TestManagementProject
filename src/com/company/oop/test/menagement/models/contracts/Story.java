package com.company.oop.test.menagement.models.contracts;

import com.company.oop.test.menagement.models.enums.PriorityType;
import com.company.oop.test.menagement.models.enums.story_enums.StorySizeType;
import com.company.oop.test.menagement.models.enums.story_enums.StoryStatusType;

public interface Story extends Task {

    PriorityType getPriority();

    StorySizeType getSize();

    StoryStatusType getStatus();

    void changePriority(PriorityType newPriorityType);

    String getAssignee();

    void setAssignee(String name);

    void changeSize(StorySizeType newSizeType);
}
