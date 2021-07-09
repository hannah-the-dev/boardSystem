package com.hannahj.springBoard.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hannahj.springBoard.domain.Board;
import com.hannahj.springBoard.domain.BoardItem;
import com.hannahj.springBoard.repository.BoardItemRepository;
import com.hannahj.springBoard.repository.BoardItemSpecs;
import com.hannahj.springBoard.repository.BoardRepository;

@Controller
@RequestMapping(value="/post")
public class BoardItemController {
	
	@Autowired
	private BoardItemRepository postRepo;
	@Autowired
	private BoardRepository boardRepo;
	
//	@Autowired
//	private BoardItemService boardItemSvc;
	
	@RequestMapping(value="/list")
	@ResponseBody
	public Optional<List<BoardItem>> findAll() {
		return Optional.of(postRepo.findAll());
		
	}
	@RequestMapping(value="/search")
	@ResponseBody
	public Optional<Page<BoardItem>> search() {
		Map<String, Object> filter = new HashMap<String, Object>();
		filter.put("title", "ff");

		PageRequest pageable = PageRequest.of(0, 10);
		Page<BoardItem> page = postRepo.findAll(BoardItemSpecs.search(filter), pageable);
		return Optional.of(page);
	}
	
   @GetMapping("/{id}") 
    public String post(
            @PathVariable("id") Long id,
            @PageableDefault(sort = { "id" }, direction = Direction.DESC) Pageable pageable, 
            Model model) {
        if (!postRepo.existsById(id)) {
        }
        Optional<BoardItem> postOptional = postRepo.findById(id);
        BoardItem post = BoardItem.builder().build();
        if (!postOptional.isPresent()) {
        } else {
            post = postOptional.get();
        }
        List<Board> boards = boardRepo.findAll();
        Page<BoardItem> comments =postRepo.findByParentId(id, pageable);
        
        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        model.addAttribute("boards", boards);
        return "/post";
    }
   
   @PostMapping("/edit")
   public String edit(
           @ModelAttribute BoardItem boardItem) {
       Optional<BoardItem> post = postRepo.findById(boardItem.getId());
       StringBuffer buff = new StringBuffer();
       
       post.ifPresentOrElse(selected ->{
           selected.setBoard(boardItem.getBoard());
           selected.setTitle(boardItem.getTitle());
           selected.setUsername(boardItem.getUsername());
           selected.setParentId(boardItem.getParentId());
           selected.setContent(boardItem.getContent());
           
           BoardItem edited = postRepo.save(selected);
           if(edited.getParentId() == null) {
               buff.append("redirect:/post/"+edited.getId()); 
           } else {
               buff.append("redirect:/post/"+edited.getParentId());
           }
       }, () -> {
           String add = (boardItem.getParentId()==null)? 
                   "redirect:/post/"+boardItem.getId() : 
                       "redirect:/post/"+boardItem.getParentId();
           buff.append(add);
           }
       );
       return buff.toString();
   }
   
   
   @PostMapping("/writer")
   public String writer(
           @ModelAttribute BoardItem boardItem) {
       BoardItem saved = postRepo.save(boardItem);
       
       if(saved.getParentId() == null) {
           String add = "redirect:/post/"+saved.getId(); 
           return add;
       } else {
           return "redirect:/post/"+saved.getParentId();
       }
   }

}
