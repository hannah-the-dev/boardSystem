package com.hannahj.springBoard.web;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hannahj.springBoard.config.auth.LoginUser;
import com.hannahj.springBoard.config.auth.dto.SessionUser;
import com.hannahj.springBoard.domain.Category;
import com.hannahj.springBoard.domain.Post;
import com.hannahj.springBoard.paging.Criteria;
import com.hannahj.springBoard.repository.CategoryRepository;
import com.hannahj.springBoard.repository.PostRepository;
import com.hannahj.springBoard.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class IndexController {
    
    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    private PostRepository postRepo;
    @Autowired
    private UserRepository userRepo;
    
    final HttpSession httpSession;

    @GetMapping({"/index", "/"}) 
    public String home() {
        
        return "/index2";
    }
    
    
    
    @GetMapping({ "/main" })
    public String categoryPage(
            @LoginUser SessionUser user,
            HttpServletResponse response,
            Model model) {
        model.addAttribute("title", "Home");
        
        Cookie cookie = new Cookie("view", null);
        cookie.setComment("게시글 조회수 체커");
        cookie.setMaxAge(60*60*24*1);
        response.addCookie(cookie);
        if(user != null) {
            model.addAttribute("user", user);
        }

        List<Category> categoryList= categoryRepo.findAll();
        model.addAttribute("list", categoryList);
        
        return "/index";
    }
    
    
    @GetMapping("/signout")
    public String signOut() {
        SessionUser user = null;
        return "/index";
    }
    
    
    
    @GetMapping("/join") 
    public String join(
            @LoginUser SessionUser user,
            Model model) {
//        if the user is login-ed, find that user info and forward to userInfo page
//        if(user != null) {
//            Optional<User> existingUser = userRepo.findByEmail(user.getEmail());
//            if(existingUser.isPresent()) {
//                model.addAttribute("user", existingUser);
//                return "/userinfo";
//            }
//        }
        httpSession.removeAttribute("id");
        httpSession.invalidate(); //세션의 모든 속성을 삭제

        model.addAttribute("user", user);
        return "/join";
    }
    
    @GetMapping({"/search", "/index/search"})
    public String search(
            @RequestParam(value="keywords") String keywords,
            @PageableDefault(sort = { "id" }, direction = Direction.DESC) Pageable pageable,
            Model model) {
            Page<Post> posts = postRepo
                   .findByParentIdIsNullAndTitleLikeIgnoreCaseOrParentIdIsNullAndContentLikeIgnoreCase(
                           '%'+ keywords+'%','%'+keywords+'%'
                   , pageable);
       
//       String[] words = keywords.split(" ");
//       Map<Long, Post> result = new LinkedHashMap<>();
//       for(String word : words) {
//           List<Post> posts = postRepo
//                   .findByParentIdIsNullAndTitleLikeIgnoreCaseOrParentIdIsNullAndContentLikeIgnoreCase(
//                           '%'+word+'%','%'+word+'%'
//                           );
//           for (Post post: posts) {
//               result.put(post.getId(), post);
//           }
//       }
//       int start = (int) pageable.getOffset();
//       int end = (start + pageable.getPageSize()) > result.size() ? result.size() :
//           (start + pageable.getPageSize());
//       Page<Post> pagedResult = new PageImpl<> (
//               (new ArrayList<Post> (result.values()))
//               .subList(start,end), pageable, pageable.getPageSize()
//               );
//       
       model.addAttribute("page", posts);
       model.addAttribute("keyword",keywords);
       Criteria criteria = new Criteria(posts);
       model.addAttribute("startBlockPage", criteria.getStartBlockPage());
       model.addAttribute("endBlockPage", criteria.getEndBlockPage());
       model.addAttribute("title", "검색 결과");       
       return "/search";       
   }
    
}
