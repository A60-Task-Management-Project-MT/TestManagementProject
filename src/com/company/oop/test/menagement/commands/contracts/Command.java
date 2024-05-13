package com.company.oop.test.menagement.commands.contracts;

import java.util.List;

public interface Command {
    String execute(List<String> parameters);

}
