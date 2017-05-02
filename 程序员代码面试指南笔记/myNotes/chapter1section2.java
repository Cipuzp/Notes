package myNotes;

import java.util.Stack;

//题目：编写一个类，用两个栈实现队列，支持队列的基本操作(add、poll、peek)
/*注意:
 * 1.需要一次性将stackPush中的数据压入到stackPop中
 * 2.如果stackPop不为空，stackPush不能向stackPop中压入数据
 * */
public class chapter1section2 {

	// 组合方法
	public static class TwoStacksQueue {
		public Stack<Integer> stackPush;// 第一个压入栈
		public Stack<Integer> stackPop;// 第二个压入栈

		// 创建两个栈
		public TwoStacksQueue() {
			stackPush = new Stack<Integer>();// 第一个压入栈
			stackPop = new Stack<Integer>();// 第二个压入栈
		}

		// add方法
		public void add(int pushInt) {
			stackPush.push(pushInt); // 先将pushInt压入到一个stackPush栈中
		}

		// poll方法
		public int poll() {
			if (stackPush.empty() && stackPop.empty()) {// 如果两个栈全部是空
				throw new RuntimeException("Queue is empty!");// 就抛出异常
			} else if (stackPop.empty()) {// 当只有stackPop栈为空时，程序可以运行
				while (!stackPush.empty()) {// 当stackPush栈不为空时
					stackPop.push(stackPush.pop());// 这时可以一直将stackPush栈中的值弹出到stackPop中
				}
			}
			return stackPop.pop();// 最后将stackPop栈中的元素弹出
		}

		// peek方法
		public int peek() {
			if (stackPush.empty() && stackPop.empty()) {// 如果两个栈全部是空
				throw new RuntimeException("Queue is empty!");// 就抛出异常
			} else if (stackPop.empty()) {// 当只有stackPop栈为空时，程序可以运行
				while (!stackPush.empty()) {// 当stackPush栈不为空时
					stackPop.push(stackPush.pop());// 这时可以一直将stackPush栈中的值弹出到stackPop中
				}
			}
			return stackPop.peek();// 返回查看stackPop栈顶对象
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TwoStacksQueue test = new TwoStacksQueue();
		test.add(1);
		test.add(2);
		test.add(3);
		System.out.println(test.peek());
		System.out.println(test.poll());
		System.out.println(test.peek());
		System.out.println(test.poll());
		System.out.println(test.peek());
		System.out.println(test.poll());
	}

}
