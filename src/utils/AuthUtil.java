package utils;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthUtil {

	public static boolean checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("isUser") == null) {
			return false;
		}
		return true;
	}

	// Check role when login
	public static String concatRole(List<String> listRole) {
		String strRole = "";
		for (String role : listRole) {
			strRole += role + "_";
		}
		return strRole;
	}
	
	public static boolean isRole (String concatRole, String ...roleCheck) {
		for (String role : roleCheck) {
			if(concatRole.contains(role)) {
				return true;
			}
		}
		return false;
		
	}

	public static void main(String[] args) {
		//String concatRole = concatRole("ROLE_ADMIN", "ROLE_EDITOR");
		//System.out.println(isRole(concatRole, "ROLE_EDITOR"));
		//System.out.println("A_B_C".contains("_C"));
	}
}
