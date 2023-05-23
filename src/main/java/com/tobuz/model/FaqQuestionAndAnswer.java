package com.tobuz.model;

import javax.persistence.Entity;

import org.springframework.ui.Model;

@Entity
public class FaqQuestionAndAnswer extends BaseEntity {

	private String question;

	private String answer;

	
	/*
	 * public static Model.Finder<Long, FaqQuestionAndAnswer> find = new
	 * Model.Finder<Long, FaqQuestionAndAnswer>( FaqQuestionAndAnswer.class);
	 */
	/*
	 * @JsonIgnore
	 * 
	 * @ManyToOne private FaqCategory faqCategory;
	 */
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		
		
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/*
	 * public FaqCategory getFaqCategory() { return faqCategory; }
	 * 
	 * public void setFaqCategory(FaqCategory faqCategory) { this.faqCategory =
	 * faqCategory; }
	 */
	/*
	 * @Override public String toString() { return "FaqQuestionAndAnswer [question="
	 * + question + ", answer=" + answer + ", faqCategory=" + faqCategory + "]"; }
	 */

	/*
	 * public static List<FaqQuestionAndAnswer> getAllFaqs() {
	 * List<FaqQuestionAndAnswer> faqQuestionAndAnswers =
	 * FaqQuestionAndAnswer.find.where() .eq("isActive", Boolean.TRUE).findList();
	 * Logger.info("============================"); return faqQuestionAndAnswers; }
	 * 
	 * public static FaqQuestionAndAnswer getFaqQuestionAndAnsById(Long qId) {
	 * return FaqQuestionAndAnswer.find.byId(qId); }
	 */
}
