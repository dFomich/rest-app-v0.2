package by.epam.javatraining.restautant.tag;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;


public class CustomTagHandler extends TagSupport {
    private static final Logger LOGGER = LogManager.getLogger(CustomTagHandler.class);

    @Override
    public int doStartTag() throws JspException {
        String message = "<b>&#169; Dmitry Fomich 2022</b>";

        try {
            JspWriter out = pageContext.getOut();
            out.write(message);
        } catch (IOException e) {
            LOGGER.error(e);
            throw new JspException(e);
        }

        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
