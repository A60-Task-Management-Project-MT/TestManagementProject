package com.company.oop.test.menagement.models.contracts;

import com.company.oop.test.menagement.models.enums.PriorityType;
import com.company.oop.test.menagement.models.enums.story_enums.StorySizeType;
import com.company.oop.test.menagement.models.enums.story_enums.StoryStatusType;

public interface Story extends Task<StoryStatusType>, Assignable {

    PriorityType getPriority();

    StorySizeType getSize();

    void changePriority(PriorityType newPriorityType);

    void changeSize(StorySizeType newSizeType);
}
