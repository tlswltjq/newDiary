package org.example.newdiary.entity;

public enum ActivityType {
    TODO_DONE(Category.TODO),
    TODO_UNDONE(Category.TODO),
    TODO_ADD(Category.TODO),
    TODO_UPDATE(Category.TODO),
    TODO_DELETE(Category.TODO),
    TODOLIST_CREATE(Category.TODOLIST),
    TODOLIST_DELETE(Category.TODOLIST),
    TODOLIST_RENAME(Category.TODOLIST);

    private final Category category;

    ActivityType(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public enum Category {
        TODO, TODOLIST
    }
}