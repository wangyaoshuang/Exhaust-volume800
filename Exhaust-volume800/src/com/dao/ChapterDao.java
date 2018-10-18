package com.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.entity.Chapter;
import com.entity.Course;
import com.entity.Question;
import com.model.Page;

@Repository
public class ChapterDao {

	@Resource
	private SessionFactory sessionFactory;
	
	@Resource
	private QuestionDao questionDao;

	
	// �����½�
	public void saveChapter(Chapter ch, Integer course_id) {
		Session session = sessionFactory.getCurrentSession();
		Course c = session.get(Course.class, course_id);
//		if(c!=null){
//			c.getChapters().add(ch);
//		}else{
//			
//		}
		c.getChapters().add(ch);
		ch.setCourse(c);
		session.save(ch);
	}

	// id����½�
	public Chapter getChapter(int id) {
		Session session = sessionFactory.getCurrentSession();
		Chapter c = session.get(Chapter.class, id);
		return c;
	}

	// �޸��½�
	public void updateChapter(Integer id, String name, Integer chapterOrder,Course course) {
		Session session = sessionFactory.getCurrentSession();
		Chapter c = session.get(Chapter.class, id);
		c.setName(name);
		c.setChapterOrder(chapterOrder);
		c.setCourse(course);
		session.update(c);
	}

	// ɾ���½�
	public void deleteChapter(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Chapter c = session.get(Chapter.class, id);
		Set<Question> sets=c.getQuestions();
		Iterator i=sets.iterator();
		while(i.hasNext()){
			Question q=(Question)i.next();
			q=session.get(Question.class, q.getId());
		}
		session.delete(c);
	}

	// ��ѯ�½�  ����name
	public Page findChapter(int currentPage,int pageSize,String param) {
		Session session = sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Chapter where name like ?");
		query.setString(0, "%"+param+"%");
		query.setMaxResults(pageSize);
		query.setFirstResult(currentPage*pageSize);
		
		Page page=new Page();
		
		int allRow =chapterNumByName(param);
		
		List<Course> list=query.list();
		
		page.setPageNo(currentPage+1);
        page.setPageSize(pageSize);
        page.setTotalRecords(allRow);
        page.setList(list);
        
        return page;
	}
	
	public int chapterNumByName(String param){
		Session session = sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Chapter where name like ?");
		query.setString(0, "%"+param+"%");
		return query.list().size();
	}
	
	public Page findChapter(int currentPage, int pageSize) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Chapter");
		query.setFirstResult(currentPage*pageSize);
		query.setMaxResults(pageSize);
		
		Page page = new Page();
        //�ܼ�¼��
        int allRow = getAllRowCount();
        //��ҳ��ѯ�����
        List<Course> list = query.list(); 

        page.setPageNo(currentPage+1);
        page.setPageSize(pageSize);
        page.setTotalRecords(allRow);
        page.setList(list);
        
        return page;
	}
	
	private int getAllRowCount() {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Chapter");
		return query.list().size();
	}

	//��ѯ�½�  ����chapterOrder
	public Chapter findChapter(int pageNum,Integer chapterOrder,Integer c_id) {
		Session session = sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Chapter where chapterOrder=? and c_id=?");
		query.setInteger(0, chapterOrder);
		query.setInteger(1,c_id);
		if(query.list().size()!=0)
			return (Chapter)query.list().get(0);
		else
			return null;
	}
	public Page queryForPage(int currentPage, int pageSize,String name) {
		Session session=sessionFactory.getCurrentSession();
		Query query = null;
		System.out.println("name2:"+name);
		if(name==null|| "".equals(name)){
			query=session.createQuery("from Chapter");
		}else{
			query=session.createQuery("from Chapter where name like ?");
			query.setString(0, "%"+name+"%");
		}
		
		query.setFirstResult(currentPage*pageSize);
		query.setMaxResults(pageSize);
		
		Page page = new Page();
        //�ܼ�¼��
        int allRow = getAllRowCount();
        //��ҳ��ѯ�����
        List<Chapter> list = query.list(); 
        page.setPageNo(currentPage+1);
        page.setPageSize(pageSize);
        page.setTotalRecords(allRow);
        page.setList(list);
        
        return page;
	}
		
}
