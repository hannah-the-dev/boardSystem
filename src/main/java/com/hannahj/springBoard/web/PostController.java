package com.hannahj.springBoard.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hannahj.springBoard.domain.Board;
import com.hannahj.springBoard.domain.Post;
import com.hannahj.springBoard.repository.PostRepository;
import com.hannahj.springBoard.repository.BoardRepository;

@Controller
@RequestMapping(value="/post")
public class PostController {
	
	@Autowired
	private PostRepository postRepo;
	@Autowired
	private BoardRepository boardRepo;
	
	@RequestMapping(value="/list")
	@ResponseBody
	public Optional<List<Post>> findAll() {
		return Optional.of(postRepo.findAll());
		
	}

   @GetMapping("/{id}") 
    public String post(
            @PathVariable("id") Long id,
            @PageableDefault(sort = { "id" }, direction = Direction.DESC) Pageable pageable, 
            Model model) {
        if (!postRepo.existsById(id)) {
        }
        Optional<Post> postOptional = postRepo.findById(id);
        Post post = Post.builder().build();
        if (!postOptional.isPresent()) {
        } else {
            post = postOptional.get();
        }
        List<Board> boards = boardRepo.findAll();
        Page<Post> comments =postRepo.findByParentId(id, pageable);
        
        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        model.addAttribute("boards", boards);
        model.addAttribute("title", "게시글");
        return "/post";
    }
   
   @PostMapping("/edit")
   public String edit(
           @ModelAttribute Post post) {
       Optional<Post> postOpt = postRepo.findById(post.getId());
       StringBuffer buff = new StringBuffer();
       
       postOpt.ifPresentOrElse(selected ->{
           selected.setBoard(post.getBoard());
           selected.setTitle(post.getTitle());
           selected.setUsername(post.getUsername());
           selected.setParentId(post.getParentId());
           selected.setContent(post.getContent());
           
           Post edited = postRepo.save(selected);
           if(edited.getParentId() == null) {
               buff.append("redirect:/post/"+edited.getId()); 
           } else {
               buff.append("redirect:/post/"+edited.getParentId());
           }
       }, () -> {
           String add = (post.getParentId()==null)? 
                   "redirect:/post/"+post.getId() : 
                       "redirect:/post/"+post.getParentId();
           buff.append(add);
           }
       );
       return buff.toString();
   }
   
   @PostMapping("/delete")
   public String delete(
           @ModelAttribute Post post) {
       Optional<Post> postOpt = postRepo.findById(post.getId());
       StringBuffer buff = new StringBuffer();
       
       postOpt.ifPresentOrElse(selected ->{
           selected.setId(post.getId());
           selected.setBoard(post.getBoard());
           selected.setTitle(post.getTitle());
           selected.setUsername(post.getUsername());
           selected.setParentId(post.getParentId());
           selected.setContent(post.getContent());
           
//           postRepo.deleteById(post.getId());
           
           postRepo.delete(selected);
           if(post.getParentId() == null) {
               buff.append("redirect:/post/"+post.getId()); 
           } else {
               buff.append("redirect:/post/"+post.getParentId());
           }
       }, () -> {
           String add = (post.getParentId()==null)? 
                   "redirect:/post/"+post.getId() : 
                       "redirect:/post/"+post.getParentId();
           buff.append(add);
       }
               );
       return buff.toString();
   }
   
   
   @PostMapping("/writer")
   public String writer(
           @ModelAttribute Post post) {
       Post saved = postRepo.save(post);
       
       if(saved.getParentId() == null) {
           String add = "redirect:/post/"+saved.getId(); 
           return add;
       } else {
           return "redirect:/post/"+saved.getParentId();
       }
   }

}
