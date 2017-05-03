package myNotes;

import java.util.Stack;

/*题目:一个栈一次压入1、2、3、4、5，那么从栈顶到栈底分别为5、4、3、2、1。
 * 将这个栈转置后，从栈顶到栈底为1、2、3、4、5，也就是实现栈中元素的逆序，
 * 但是只能用递归函数来实现，不能用其他数据结构
 *****************************************************/
public class chapter1section3 {

	// 将栈stack的栈底元素返回并移除(栈内元素<栈底>1、2、3<栈顶>变为2、3<栈顶>)
	public static int getAndRemoveLastElement(Stack<Integer> stack) {
		int result = stack.pop(); // 将stack栈中移除出来的元素存入到result中
		if (stack.isEmpty()) {
			return result;
		} else {
			int last = getAndRemoveLastElement(stack);// 递归调用getAndRemoveLastElement方法将stack出栈的元素存到last中
			stack.push(result);// 回溯，将其它元素重新压回栈
			return last;// 第一轮时，返回栈底元素1
		}
	}

	 /**
     * 逆序一个栈:每层递归取出栈底的元素并缓存到变量中，直到栈空；
     * 然后逆向将每层变量压入栈，最后实现原栈数据的逆序。
     ***/
	public static void reverse(Stack<Integer> stack) {
		if (stack.isEmpty()) {
			return;
		} else {
			int i = getAndRemoveLastElement(stack);// 得到栈底元素,依次返回1、2、3
			reverse(stack);//递归，所以i依次为1,2,3
			stack.push(i);// 回溯，依次压入3,2,1
		}
	}

	public static void main(String[] args) {
		Stack<Integer> test = new Stack<Integer>();// Stack继承Vector，默认容量是10
		test.push(1);
		test.push(2);
		test.push(3);
		test.push(4);
		test.push(5);
		reverse(test);
		while (!test.isEmpty()) {
			System.out.println(test.pop());
		}
	}

}
