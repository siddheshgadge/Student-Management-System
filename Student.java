class Student {
	private int rno;
	private String name;
	private int sub1Marks;
	private int sub2Marks;
	private int sub3Marks;

	public Student() {}

	public Student(int rno, String name, int sub1Marks, int sub2Marks, int sub3Marks) {
		this.rno = rno;
		this.name = name;
		this.sub1Marks = sub1Marks;
		this.sub2Marks = sub2Marks;
		this.sub3Marks = sub3Marks;
	}

	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getSub1Marks() {
		return sub1Marks;
	}
	public void setSub1Marks(int sub1Marks) {
		this.sub1Marks = sub1Marks;
	}

	public int getSub2Marks() {
		return sub2Marks;
	}
	public void setSub2Marks(int sub2Marks) {
		this.sub2Marks = sub2Marks;
	}

	public int getSub3Marks() {
		return sub3Marks;
	}
	public void setSub3Marks(int sub3Marks) {
		this.sub3Marks = sub3Marks;
	}
	
	
}