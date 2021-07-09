package com.hannahj.springBoard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hannahj.springBoard.domain.Sample;
import com.hannahj.springBoard.repository.SampleRepository;
import com.hannahj.springBoard.repository.SampleRepositoryImpl;
import com.hannahj.springBoard.repository.SampleSpecs;

@SpringBootTest
public class SampleTest {
	
	@Autowired
	private SampleRepository sampleRepository;
	
//	private SampleSpecs sampleSpecs;
	
	@RequestMapping(value = "/sample/list")
	@ResponseBody
	public List<Sample> list(Model model) {
		return sampleRepository.findAll();
	}
	
	
//	@RequestMapping(value = "/sample/pageable")
//	@ResponseBody
//	@Test
	public void findAll() {
		Map<String, Object> filter = new HashMap<String, Object>();
		filter.put("title", "문의게시판");
		filter.put("id", "1");

		PageRequest pageable = PageRequest.of(0, 10);
		System.out.println("->");
		Page<Sample> page = sampleRepository.findAll(SampleSpecs.search(filter), pageable);
		for (Sample sample: page) {
			System.out.println(sample.getId());
		}
		System.out.println("<-");
	}
//	@RequestMapping(value = "/sample/search")
//	@ResponseBody
//	@Test
//	public void search(String title) {
//		Map<String, Object> filter = new HashMap<String, Object>();
//		filter.put("title", title);
//		
//		Pageable pageable = PageRequest.of(0, 10);
//		Page<Sample> page = sampleRepository.findAll(sampleSpecs.search(filter), pageable);
//		
//		for(Sample sample: page) {
//			System.out.println(sample.getTitle());
//		}
//	}

	@Autowired
	private SampleRepositoryImpl impl;
	
	@Test
	public void findAllAop() {
	    impl.findAllAop();
	    System.out.println(sampleRepository.findAllAop());
	}
	
//	@Test
	public void findByIdAop() {
	    impl.findByIdAop(30L);
	    sampleRepository.findByIdAop(30L);
	}
	
	
	
	
}
