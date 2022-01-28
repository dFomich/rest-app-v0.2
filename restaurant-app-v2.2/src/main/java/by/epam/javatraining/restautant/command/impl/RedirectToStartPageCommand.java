package by.epam.javatraining.restautant.command.impl;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.javatraining.restautant.command.Command;
import by.epam.javatraining.restautant.command.JSPParameter;
import by.epam.javatraining.restautant.command.PageType;
import by.epam.javatraining.restautant.entity.Position;
import by.epam.javatraining.restautant.util.PositionCash;

public class RedirectToStartPageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Position> positionList = PositionCash.getInstance().getPositionList();
        request.setAttribute(JSPParameter.POSITIONS.getValue(), positionList);

        return PageType.START_PAGE.getValue();
    }
}
