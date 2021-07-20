//package com.hannahj.springBoard.web;
//
//import java.util.List;
//import java.util.Optional;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.hannahj.springBoard.domain.User;
//import com.hannahj.springBoard.repository.UserRepository;
//
//@Component
//public class SignInInterceptor implements HandlerInterceptor {
//    public List mustSignIn
//        = List.of(
//                "/userinfo/**", "/admin", 
//                "/post/**/delete", "/post/**/edit"
//                );
//    
//    @Autowired
//    UserRepository userRepo;
//            
//    private static final Logger logger = LoggerFactory.getLogger(SignInInterceptor.class);
//
//    @Override
//    public boolean preHandle(
//            HttpServletRequest request, 
//            HttpServletResponse response, 
//            Object handler
//            ) throws Exception {
//        String signInEmail = 
//                (String) request.getSession().getAttribute("email");
//        
//        if(signInEmail != null) {
//            User signInUser;
//            Optional<User> optUser = userRepo.findByEmail(signInEmail);
//            if (optUser.isPresent()) {
//                signInUser = optUser.get();
//                request.getSession().setAttribute("User", signInUser);
//            }
//            return true;
//        } else {
//            String uri = request.getRequestURI();
//            String query = request.getQueryString();
//            String dest = (query == null) ? uri : uri+"?"+query;
//            request.getSession().setAttribute("dest", dest);
//        
//            response.sendRedirect("/login");
//            return false;
//        }
//    }
//    
//    @Override
//    public void postHandle(
//            HttpServletRequest request, 
//            HttpServletResponse response,
//            Object handler,
//            ModelAndView modelAndView
//            ) throws Exception {
//    }
//
//    @Override
//    public void afterCompletion(
//            HttpServletRequest request, 
//            HttpServletResponse response, 
//            Object handler, 
//            Exception ex
//            ) throws Exception {
//    }
//}
