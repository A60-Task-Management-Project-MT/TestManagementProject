package com.company.oop.test.menagement.core.contracts;

import com.company.oop.test.menagement.commands.contracts.Command;

public interface CommandFactory {
    Command createCommandFromCommandName(String commandTypeAsString, TaskManagementRepository taskManagementRepository);
}
