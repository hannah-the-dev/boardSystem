package com.hannahj.springBoard.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Formula;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
@Entity
public class Post extends BaseTimeEntity {
	
    @ManyToOne(optional=false)
    @JoinColumn(name="board_id")
	private Board board;  //where it is nested

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@Column
	private String username;
	
	@Column
	private String title;
	@Column
	private String content;
	@Column
	private Long parentId;
	
	@Column
	private int hit;
	
//	private List<Post> comment;
	
	@Formula("(select count(1) from post as bc where bc.parent_id = id)")
	private Long commentSize;

}

