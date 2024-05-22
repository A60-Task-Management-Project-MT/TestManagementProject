# Self Project One - Task Management System

## Description

A `Tasks Management System` console application to be used by a small team of developers, who need to keep track of all
the
tasks,
surrounding a software product they are building

## Models

> Note: All validation intervals are inclusive (closed).

### **Bug**

- `id` - an int, must be unique
- `title` - a string between `10` and `100` symbols.
- `description` - a string between `10` and `500` symbols.
- `steps` - a collection of strings showing how to reproduce the bug.
- `priority` - one of the following: **High**, **Medium** or **Low**.
- `severity` - one of the following: **Critical**, **Major** or **Minor**.
- `status` - one of the following: **Active** or **Done**.
- `assignee` - a string, the name of a member from a team.
- `comments` - a collection of comments (string messages with an author).
- `activity history` - a collection of all changes (string messages) that were done to the bug.

It is convertible to **String** in the format using method viewInfo():

```none
Task ID: #
  #Type: {type}
  #Title: {title}
  #Description: {description}
  #Status: {status}
  #Priority: {priority}
  #Severity: {severity}
  #Assignee: {assignee}
  #StepsToReproduce:
    - 1. step
    ...
~~~ COMMENTS ~~~
   1. ...
   2. ...
    # NO COMMENTS AVAILABLE - if there are not comments available
```

### **Story**

- `id` - a long, must be unique
- `title` - a string between `10` and `100` symbols.
- `description` - a string between `10` and `500` symbols.
- `priority` - one of the following: **High**, **Medium** or **Low**.
- `size` - one of the following: **Large**, **Medium** or **Small**.
- `status` - one of the following: **Not Done**, **In Progress** or **Done**.
- `assignee` - a string, the name of a member from a team.
- `comments` - a collection of comments (string messages with an author).
- `activity history` - a collection of all changes (string messages) that were done to the bug.

It is convertible to **String** in the format using method viewInfo():

```none
Task ID#
  #Type: {type}
  #Title: {title}
  #Description: {description}
  #Status: {status}
  #Priority: {priority}
  #Size: {size}
  #Assignee: {assignee}
~~~ COMMENTS ~~~
   1. ...
   2. ...
    # NO COMMENTS AVAILABLE - if there are not comments available
```

### **Feedback**

- `id` - a long, must be unique
- `title` - a string between `10` and `100` symbols.
- `description` - a string between `10` and `500` symbols.
- `rating` - an integer.
- `status` - one of the following: **New**, **Unscheduled**, **Scheduled** or **Done**.
- `comments` - a collection of comments (string messages with an author).
- `activity history` - a collection of all changes (string messages) that were done to the bug.

It is convertible to **String** in the format using method viewInfo():

```none
Task ID#
  #Type: {type}
  #Title: {title}
  #Description: {description}
  #Rating: {rating}
  #Status: {status}

```

### **Comment**

- `author` - a string between `5` and `15` symbols.
- `content` - a string between `3` and `200` symbols.

It is convertible to **String** in the format:

```none
**********
{Content}
Author: {Comment username}
**********
```

### **ActivityHistory**

- `description` - cannot be empty.

It is convertible to **String** in the format:

```none
[{event timestamp}] {event description}
```

### **Board**

- `name` - a string between `5` and `10` symbols, must be unique in a team.
- `tasks` - a collection of tasks
- `activity history` - a collection of all changes (string messages) that were done to the board.

####

- Printing
    - For the `Board` class: `"BOARD: {name}, TOTAL TASKS: {count of assigned tasks}"`
    - For all tasks of a board:

```none
~~~ BOARD NAME TASKS ~~~
Task ID#
  #Type: {type}
  #Title: {title}
  #Description: {description}
  #Status: {status}
  #Priority: {priority}
  #Severity: {severity}
  #AssignedTo: {assignee}
  #StepsToReproduce:
    1. step
    ...
```

- For all events of a board:

```none
~~~ BOARD NAME HISTORY ~~~
[{event timestamp}] {event description}
```

### **Member**

- `name` - a string between `5` and `15` symbols, must be unique in the application.
- `tasks` - a collection of tasks
- `activity history` - a collection of all changes (string messages) that were done to the board.

####

- Printing
    

```none
~~~ MEMBER NAME TASKS ~~~
1. Task ID#
    #Type: {type}
    #Title: {title}
    #Description: {description}
    #Status: {status}
    #Priority: {priority}
    #Severity: {severity}
    #AssignedTo: {assignee}
    #StepsToReproduce:
      1. step
      ...
```

- For all events of a member:

```none
~~~ MEMBER NAME HISTORY ~~~
[{event timestamp}] {event description}
```

### **Team**

- `name` - a string between `5` and `15` symbols, must be unique in the application.
- `members` - a collection of employees
- `boards` - a collection of boards.

####

- For all events of a team

```none
~~~ TEAM NAME BOARDS ACTIVITY ~~~
1. ~~~ BOARD NAME HISTORY ~~~
[{event timestamp}] {event description}
~~~ TEAM SECONDTEAM MEMBERS ACTIVITY ~~~
1. ~~~ MEMBER NAME HISTORY ~~~
[{event timestamp}] {event description}
```

####



## Commands

Currently, the engine supports the following commands:

- **CreatePerson** `{[memberName]}` - Creates a new person if not already in the system.
- **CreateTeam** `[teamName]` - Creates a new team if not already in the system.
- **CreateBoard** `[boardName] [teamName]` - Adds a new board in a specific team.
- **CreateBug** `{[title]} {[description]} [priority] [severity] [assignee] [boardName] {[steps]}` - Creates a new assigned bug task
  and adds it to a specific board.
- **CreateStory** `{[title]} {[description]} [priority] [size] [assignee] [boardName]` - Creates a new assigned story task and adds
  it to a specific board.
- **CreateFeedback** `{[title]} {[description]} [rating] [boardName]` - Creates a new feedback task and adds it to a
  specific board.
- **AddPersonToTeam** `[memberName] [teamName]` - Adds a member to a specific team if both of them already exists.
- **AddComment** `[author] {[description]} [taskID]` - Adds a comment with the content provided to the task with that id (
  if the task exists) and sets the author.
- **ChangeBugPriority** `[taskID] [priority]` - Changes the bug priority with given ID
- **ChangeStoryPriority** `[taskID] [priority]` - Changes the story priority with given ID
- **ChangeFeedbackRating** `[taskID] [rating]` - Changes the rating of a feedback task with given ID
- **ChangeBugSeverity** `[taskID] [severity]` - Changes the severity of a bug task with given ID
- **ChangeStorySize** `[taskID] [size]` - Changes the size of a story task with given ID
- **ChangeTaskStatus** `[taskID]` - Advance the status of a task with a given ID
- **AssignTask** `[taskType] [filterWord] [memberName]` - Assign tasks to a member and adds them to his list. 
Can assign a specific task or a list of tasks chosen by a given condition.
- **UnAssignTask**  - Removes all tasks with status "Done".
- **ShowTeams** - shows all the teams in the database.
- **ShowPeople** - shows all the employees in the database.
- **ShowTeamBoards** `[teamName]`- shows all the boards created in a specific team.
- **ShowTeamMembers** `[teamName]`- shows all the members of a specific team.
- **ShowTeamActivity** `[teamName]`- shows each member and each board activity history of a specific team.
- **ShowBoardActivity** `[boardName]`- shows all the events tied to a specific task.
- **ShowPersonActivity** `[personName]`- shows all the events tide to a specific member.
- **ListAllTasks** `[]` / `[Sort] {[Title]}` / `[Filter] {[filterWord]}` - lists all stored tasks - directly or sorted by 
title and/or filtered by title from given word or sequence of letters.
- **ListBugs** `[]` / `[Sort] {[filterWord]}` / `[Filter] {[filterWord]}` - lists all stored bugs. Can be filtered
  by `Status` and/or `Assignee`. Can be sorted by `Title`, `Priority` or `Severity`.
- **ListStories** `[]` / `[Sort] {[filterWord]}` / `[Filter] {[filterWord]}` - lists all stored stories. Can be
  filtered by `Status` and/or `Assignee`. Can be sorted by `Title`, `Priority` or `Size`.
- **ListFeedbacks** `[]` / `[sortBy] [filterBy]` - lists all stored feedbacks. Can be filtered by `Status`. Can be sorted
  by `Title` or `Rating`.
- **ListTasksWithAssignee** `[]` / `[Sort] {[filterWord]}` / `[Filter] {[filterWord]}`- lists all assigned tasks. Can
  be filtered by `Status` and/or `Assignee`. Can be sorted  by `Title`.

> Note: All commands return **appropriate success messages**. The `Engine` returns **appropriate error messages** in
> case of an invalid operation or error. All command parameters which are surrounded by {} should be used in this way.
> Example: ListBugs Filter {Active}. All Filter and Sort words should be with capital letters.

## Use cases

#### Use case #1

One of the developers has noticed a bug in the company’s product.
He starts the application and goes on to create a new Task for it.
He creates a new Bug and gives it the title "The program freezes when the Log In button is clicked."
For the description he adds "This needs to be fixed quickly!", he marks the Bug as High priority and
gives it Critical severity. Since it is a new bug, it gets the Active status.
The developer also assigns it to the senior developer in the team. To be able to fix the bug,
the senior developer needs to know how to reproduce it, so the developer who logged the bug
adds a list of steps to reproduce: "1. Open the application; 2. Click "Log In"; 3. The application freezes!"
The bug is saved to the application and is ready to be fixed.

#### Use case #2

A new developer has joined the team. One of the other developers starts the application and creates a
new team member. After that, he adds the new team member to one of the existing teams and assigns all
Critical bugs to him.

#### Use case #3

One of the developers has fixed a bug that was assigned to him. He adds a comment to that bug,
saying "This one took me a while, but it is fixed now!", and then changes the status of the bug to Done.
Just to be sure, he checks the changes history list of the bug and sees that the last entry in the list
says, "The status of item with ID 42 switched from Active to Done."

## Constraints

//TODO

## Unit Tests

//TODO

## Technical Requirements

1. **Follow the OOP best practices**:

    - Use data encapsulation.
    - Use inheritance and polymorphism properly.
    - Use interfaces and abstract classes properly.
    - Use static members properly.
    - Use enumerations properly.
    - Aim for strong cohesion and loose coupling.

2. **Follow guidelines for writing clean code**:
    - Proper naming of classes, methods, and fields.
    - Small classes and methods.
    - Well formatted and consistent code.
    - No duplicate code.

3. **Implement proper user input validation and display meaningful user messages**.
4. **Implement proper exception handling**.
5. **Prefer using the Streaming API, then using ordinary loops, wherever you can**.
6. **Cover the core functionality with unit tests (at least 80% code coverage of the models and commands)**.
7. **Use Git to keep your source code and for team collaboration**.

