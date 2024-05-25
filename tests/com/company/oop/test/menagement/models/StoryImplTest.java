package com.company.oop.test.menagement.models;

import com.company.oop.test.menagement.models.contracts.ActivityHistory;
import com.company.oop.test.menagement.models.contracts.Comment;
import com.company.oop.test.menagement.models.contracts.Story;
import com.company.oop.test.menagement.models.enums.PriorityType;
import com.company.oop.test.menagement.models.enums.TaskType;
import com.company.oop.test.menagement.models.enums.story_enums.StorySizeType;
import com.company.oop.test.menagement.models.enums.story_enums.StoryStatusType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StoryImplTest {
    public static final String VALID_TITLE_NAME_LENGTH = "C".repeat(100);
    public static final String VALID_DESCRIPTION_NAME_LENGTH = "C".repeat(500);
    public static final String INVALID_TITLE_LENGTH = "C".repeat(101);
    public static final String INVALID_DESCRIPTION_NAME_LENGTH = "C".repeat(501);



    @Test
    void constructor_ShouldCreate_Story_WhenArguments_AreValid() {
        Story story = new StoryImpl(1, VALID_TITLE_NAME_LENGTH, VALID_DESCRIPTION_NAME_LENGTH,
                PriorityType.MEDIUM, StorySizeType.LARGE, "Pesho");

        assertAll(
                () -> assertEquals(1, story.getId()),
                () -> assertEquals(VALID_TITLE_NAME_LENGTH, story.getTitle()),
                () -> assertEquals(VALID_DESCRIPTION_NAME_LENGTH, story.getDescription()),
                () -> assertEquals(PriorityType.MEDIUM, story.getPriority()),
                () -> assertEquals(StorySizeType.LARGE, story.getSize()),
                () -> assertEquals("Pesho", story.getAssignee()),
                () -> assertEquals(TaskType.STORY, story.getTaskType()),
                () -> assertEquals(0, story.getComments().size())
        );
    }

    @Test
    void constructor_ShouldCreate_Story_WithFirstStatus_NotDone() {
        Story story = new StoryImpl(1, VALID_TITLE_NAME_LENGTH, VALID_DESCRIPTION_NAME_LENGTH,
                PriorityType.MEDIUM, StorySizeType.LARGE, "Pesho");

        assertEquals(StoryStatusType.NOT_DONE, story.getStatus());
    }

    @Test
    void constructor_ShouldThrowException_When_TitleLength_IsNotValid() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new StoryImpl(1, INVALID_TITLE_LENGTH, VALID_DESCRIPTION_NAME_LENGTH,
                        PriorityType.MEDIUM, StorySizeType.LARGE, "Pesho"));
    }

    @Test
    void constructor_ShouldThrowException_When_DescriptionLength_IsNotValid() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new StoryImpl(1, VALID_TITLE_NAME_LENGTH, INVALID_DESCRIPTION_NAME_LENGTH,
                        PriorityType.MEDIUM, StorySizeType.LARGE, "Pesho"));
    }

    @Test
    void addComment_ShouldAddComments() {
        Story story = new StoryImpl(1, VALID_TITLE_NAME_LENGTH, VALID_DESCRIPTION_NAME_LENGTH,
                PriorityType.MEDIUM, StorySizeType.LARGE, "Pesho");
        Comment comment = new CommentImpl("Gosho", "Neveroqtno");

        story.addComment(comment);
        assertEquals(1, story.getComments().size());
    }

    @Test
    public void getComments_Should_ReturnCopyOfTheCollection() {
        Story story = new StoryImpl(1, VALID_TITLE_NAME_LENGTH, VALID_DESCRIPTION_NAME_LENGTH,
                PriorityType.MEDIUM, StorySizeType.LARGE, "Pesho");
        Comment comment = new CommentImpl("Gosho", "Neveroqtno");

        story.getComments().add(comment);

        Assertions.assertEquals(0, story.getComments().size());
    }

    @Test
    public void getHistory_Should_ReturnCopyOfTheCollection() {
        Story story = new StoryImpl(1, VALID_TITLE_NAME_LENGTH, VALID_DESCRIPTION_NAME_LENGTH,
                PriorityType.MEDIUM, StorySizeType.LARGE, "Pesho");
        ActivityHistory activityHistory = new ActivityHistoryImpl("Description");

        story.getHistory().add(activityHistory);

        //Очакваме да върне копие от колекция и прави точно това. Просто при инициализиране на ново Story вкарва 1 ActivityHistory веднага в листа.
        Assertions.assertEquals(1, story.getHistory().size());
    }

    @Test
    void equalsMethod_Should_ReturnFalse_When_TwoDifferentStories_AreMade() {
        Story story = new StoryImpl(1, VALID_TITLE_NAME_LENGTH, VALID_DESCRIPTION_NAME_LENGTH,
                PriorityType.MEDIUM, StorySizeType.LARGE, "Pesho");
        Story story2 = new StoryImpl(2, VALID_TITLE_NAME_LENGTH, VALID_DESCRIPTION_NAME_LENGTH,
                PriorityType.HIGH, StorySizeType.SMALL, "Gosho");

        assertNotEquals(story, story2);
    }
}