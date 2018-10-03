/**
 * 
 */

package fr.inria.convecs.iotcomposer.util;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author silverquick
 *
 */
public class ComposerExceptionMapper {

  private static final Logger LOGGER = LoggerFactory.getLogger(ComposerExceptionMapper.class);

  /**
   * default constructor
   */
  protected ComposerExceptionMapper() {

  }

  /**
   * Custom exception handler.
   * TODO: Cleanup exception handling across project
   * @param exception
   *          app exception
   * @return WebApplicationException
   */

  public static WebApplicationException createWebAppException(final Exception exception) {
    WebApplicationException webApplicationException = null;
    String exceptionMessage = new StringBuilder("Error processing the request. Please contact the team")
    		.append("\n \n")
    		.append("The exception message is: ")
    		.append("\n")
    		.append(exception.getMessage()).toString();
    if (exception instanceof IllegalArgumentException) {
      LOGGER.error("IllegalArgumentException ", exception);
      webApplicationException = new WebApplicationException(
          Response.status(Response.Status.BAD_REQUEST).entity(exceptionMessage).build());
    } else if (exception instanceof IllegalStateException) {
      LOGGER.error("IllegalStateException ", exception);
      webApplicationException = new WebApplicationException(
          Response.status(Response.Status.CONFLICT).entity(exceptionMessage).build());
    } else {
      LOGGER.error("Internal server errror", exception);
  
      webApplicationException = new WebApplicationException(Response
          .status(Response.Status.INTERNAL_SERVER_ERROR).entity(exceptionMessage).build());
    }
    return webApplicationException;
  }

}
