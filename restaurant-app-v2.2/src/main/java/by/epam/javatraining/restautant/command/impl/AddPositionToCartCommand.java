package by.epam.javatraining.restautant.command.impl;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.javatraining.restautant.command.Command;
import by.epam.javatraining.restautant.command.JSPParameter;
import by.epam.javatraining.restautant.command.PageType;
import by.epam.javatraining.restautant.entity.Position;
import by.epam.javatraining.restautant.util.PositionCash;

public class AddPositionToCartCommand implements Command {
    private static final int DEFAULT_POSITION_QUANTITY = 1;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        Map<Position, Integer> cartMap = (Map<Position, Integer>) session
                .getAttribute(JSPParameter.SESSION_POSITIONS.getValue());
        if (cartMap == null) {
            cartMap = new HashMap<>();
        }

        int positionId = Integer.parseInt(request.getParameter(JSPParameter.ID.getValue()));
        PositionCash cash = PositionCash.getInstance();
        Position position = cash.getPositionById(positionId);
        addPosition(position, cartMap);
        session.setAttribute(JSPParameter.SESSION_POSITIONS.getValue(), cartMap);

        return PageType.START_PAGE.getValue();
    }

    private void addPosition(Position position, Map<Position, Integer> cartMap) {
       if (cartMap.containsKey(position)) {
           cartMap.put(position, cartMap.get(position) + DEFAULT_POSITION_QUANTITY);
       } else {
           cartMap.put(position, DEFAULT_POSITION_QUANTITY);
       }
    }
}
