package by.epam.javatraining.restautant.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.javatraining.restautant.command.Command;
import by.epam.javatraining.restautant.command.PageType;


public class ViewAdminProfileCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return PageType.ADMIN_START_PAGE.getValue();
    }
}
