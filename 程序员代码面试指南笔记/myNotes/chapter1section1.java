package myNotes;

import java.util.Stack;

import chapter_1_stackandqueue.Problem_01_GetMinStack.MyStack1;

//题目：实现一个特殊的栈，在实现栈的基本功能的基础上，再实现返回栈中最小元素的操作

public class chapter1section1 {
	//方法一
	public static class MyStack1{
		private Stack<Integer> stackData;//一个栈用来保存当前占中的元素，其功能和一个正常的栈没有区别，这个栈记为stackData
		private Stack<Integer> stackMin;//另一个栈用来保存每一步的最小值，这个栈记为stackMin
		//创建两个栈
		public MyStack1() {
			this.stackData = new Stack<Integer>();//开辟一个stackData栈
			this.stackMin = new Stack<Integer>();//开辟一个stackMin栈
		}
		
		//入栈方法
		public void push(int newNum){ //假设数据当前数据为newNum
			//判断stackMin是否为空
			if(this.stackMin.isEmpty()){  //如果stackMin为空
				this.stackMin.push(newNum);//则将newNum压入stackMin栈中
			}else if(newNum<=this.getMin()){//否则stackMin不为空，并判断newNum是否小于stackMin的栈顶元素
				this.stackMin.push(newNum);//如果newNum小于stackMin的栈顶元素，则将newNum压入栈中，反之不压入
			}
			this.stackData.push(newNum);//不管结果如何，都先将newNum先压入stackData中
		}
		
		//出栈方法
		public int pop(){         //int型需要返回值
			//判断stackData是否为空
			if(this.stackData.isEmpty()){//如果stackData为空
				throw new RuntimeException("your stack is empty.");//则抛出为空异常，并打印异常消息
			}
			int value=this.stackData.pop();//将stackData的栈顶元素弹出并记为value
			//判断value和stackMin的栈顶元素的大小
			if(value==this.getMin()){   //如果相等
				this.stackMin.pop();//stackMin弹出栈顶元素
			}
			return value;
		}
		

		//取得stackMin的栈顶元素，也就是当前的最小元素
		public int getMin(){    
			if(this.stackMin.isEmpty()){  //如果为空
				throw new RuntimeException("your stack is empty.");//则抛出为空异常，并打印异常消息
			}
			return this.stackMin.peek();//返回查看栈顶对象，这个栈顶对象记为最小
		}

	}

	//方法二
	public static class MyStack2{
		private Stack<Integer> stackData;//一个栈用来保存当前占中的元素，其功能和一个正常的栈没有区别，这个栈记为stackData
		private Stack<Integer> stackMin;//另一个栈用来保存每一步的最小值，这个栈记为stackMin

		//创建两个栈
		public MyStack2() {
			this.stackData = new Stack<Integer>();//开辟一个stackData栈
			this.stackMin = new Stack<Integer>();//开辟一个stackMin栈
		}
		
		//入栈方法
		public void push(int newNum){ //假设数据当前数据为newNum
			//判断stackMin是否为空
			if(this.stackMin.isEmpty()){  //如果stackMin为空
				this.stackMin.push(newNum);//则将newNum压入stackMin栈中
			}else if(newNum<=this.getMin()){//否则stackMin不为空，并判断newNum是否小于stackMin的栈顶元素
				this.stackMin.push(newNum);//如果newNum小于stackMin的栈顶元素，则将newNum压入栈中，反之不压入
			}else{  //如果newNum大于stackMin的栈顶元素
				int newMin=this.stackMin.peek();//则将stackMin栈顶元素存放到newMin中
				this.stackMin.push(newMin);//并将stackMin栈顶元素上再压入栈顶元素
			}
			this.stackData.push(newNum);//不管结果如何，都先将newNum先压入stackData中
		}
		
		//出栈方法
		public int pop(){         //int型需要返回值
			//判断stackData是否为空
			if(this.stackData.isEmpty()){//如果stackData为空
				throw new RuntimeException("your stack is empty.");//则抛出为空异常，并打印异常消息
			}
			int value=this.stackData.pop();//将stackData的栈顶元素弹出并记为value
			this.stackMin.pop();//stackMin弹出栈顶元素
			return value;
		}
		
		//取得stackMin的栈顶元素，也就是当前的最小元素
		public int getMin(){    
			if(this.stackMin.isEmpty()){  //如果为空
				throw new RuntimeException("your stack is empty.");//则抛出为空异常，并打印异常消息
			}
			return this.stackMin.peek();//返回查看栈顶对象，这个栈顶对象记为最小
		}
	}
	
	public static void main(String[] args) {
		MyStack1 stack1 = new MyStack1();
		stack1.push(3);
		System.out.println(stack1.getMin());
		stack1.push(4);
		System.out.println(stack1.getMin());
		stack1.push(1);
		System.out.println(stack1.getMin());
		System.out.println(stack1.pop());
		System.out.println(stack1.getMin());

		System.out.println("=============");

		MyStack1 stack2 = new MyStack1();
		stack2.push(3);
		System.out.println(stack2.getMin());
		stack2.push(4);
		System.out.println(stack2.getMin());
		stack2.push(1);
		System.out.println(stack2.getMin());
		System.out.println(stack2.pop());
		System.out.println(stack2.getMin());
	}
	
}

