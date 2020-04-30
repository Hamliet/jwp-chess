package wooteco.chess.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import wooteco.chess.service.ChessService;
import wooteco.chess.webutil.ModelParser;

import javax.servlet.ServletRequest;
import java.sql.SQLException;

@Controller
public class ChessController {

    @Autowired
    ChessService chessService;

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("index");
        view.addAllObjects(chessService.loadInitBoard());
        return view;
    }

    @PostMapping("/new-game")
    public ModelAndView newGame() throws SQLException {
        ModelAndView view = new ModelAndView("index");
        view.addAllObjects(ModelParser.parseBoard(chessService.newGame()));
        return view;
    }

    @PostMapping("/load-game")
    public ModelAndView loadGame() throws SQLException {
        ModelAndView view = new ModelAndView("index");
        view.addAllObjects(ModelParser.parseBoard(chessService.readBoard()));
        return view;
    }

    @PostMapping("/score")
    public ModelAndView score() throws SQLException {
        ModelAndView view = new ModelAndView("index");
        view.addAllObjects(chessService.readBoardWithScore());
        return view;
    }

    @PostMapping("/move")
    public ModelAndView move(ServletRequest request) throws SQLException {
        ModelAndView view = new ModelAndView("index");

        String start = request.getParameter("start");
        String end = request.getParameter("end");
        chessService.move(start, end);

        view.addAllObjects(ModelParser.parseBoard(chessService.readBoard()));
        return view;
    }

    @PostMapping("/show-movable")
    public ModelAndView showMovable(ServletRequest request) throws SQLException {
        ModelAndView view = new ModelAndView("index");
        view.addAllObjects(chessService.loadMovable(request.getParameter("start")));
        return view;
    }
}