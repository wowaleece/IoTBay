package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/CSS/CSS.css\">\r\n");
      out.write("        ");
session.invalidate();
      out.write("\r\n");
      out.write("        <title>Home</title>\r\n");
      out.write("    </head>\r\n");
      out.write("    \r\n");
      out.write("    <body>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("        \r\n");
      out.write("        <div id=\"mySidenav\" class=\"sidenav\">\r\n");
      out.write("            <a href=\"javascript:void(0)\" class=\"closebtn\" onclick=\"closeNav()\">&times;</a>\r\n");
      out.write("            <a href=\"index.jsp\">Home</a>\r\n");
      out.write("            <a href=\"register.jsp\">Register</a>\r\n");
      out.write("            <a href=\"login.jsp\"\">Login</a>\r\n");
      out.write("            <a href=\"mainGuest.jsp\"\">Browse Products</a>\r\n");
      out.write("          </div>\r\n");
      out.write("       \r\n");
      out.write("        <span style=\"font-size:30px;cursor:pointer;color:#FFFFFF;\" onclick=\"openNav()\">&#9776; Menu </span>\r\n");
      out.write("    \r\n");
      out.write("        <script>\r\n");
      out.write("        function openNav() {\r\n");
      out.write("          document.getElementById(\"mySidenav\").style.width = \"250px\";\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        function closeNav() {\r\n");
      out.write("          document.getElementById(\"mySidenav\").style.width = \"0\";\r\n");
      out.write("        }\r\n");
      out.write("        \r\n");
      out.write("        </script>\r\n");
      out.write("            <h1>IoTBay Home</h1>\r\n");
      out.write("        <div>Welcome to the IoTBay Web Application!</div>\r\n");
      out.write("        <br  />\r\n");
      out.write("        \r\n");
      out.write("        <div>\r\n");
      out.write("            <a class=\"button\" href=\"register.jsp\">Register</a>\r\n");
      out.write("        </div>\r\n");
      out.write("        <br  />\r\n");
      out.write("        <div>\r\n");
      out.write("            <a class=\"button\" href=\"login.jsp\">Log In</a>\r\n");
      out.write("        </div>        \r\n");
      out.write("        <br  />\r\n");
      out.write("        <div>\r\n");
      out.write("            <a class=\"button\" href=\"mainGuest.jsp\">Guest Enter</a>\r\n");
      out.write("        </div>\r\n");
      out.write("        \r\n");
      out.write("        \r\n");
      out.write("        ");
      out.write("\r\n");
      out.write("        \r\n");
      out.write("\r\n");
      out.write("        \r\n");
      out.write("    </body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
