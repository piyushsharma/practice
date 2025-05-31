package com.dsandalgos.hackerrank;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Tieu owns a pizza restaurant and he manages it in his own way. While in a normal restaurant, a customer is served by following the first-come, first-served rule, Tieu simply minimizes the average waiting time of his customers. So he gets to decide who is served first, regardless of how sooner or later a person comes.
 *
 * Different kinds of pizzas take different amounts of time to cook. Also, once he starts cooking a pizza, he cannot cook another pizza until the first pizza is completely cooked. Let's say we have three customers who come at time t=0, t=1, & t=2 respectively, and the time needed to cook their pizzas is 3, 9, & 6 respectively. If Tieu applies first-come, first-served rule, then the waiting time of three customers is 3, 11, & 16 respectively. The average waiting time in this case is (3 + 11 + 16) / 3 = 10. This is not an optimized solution. After serving the first customer at time t=3, Tieu can choose to serve the third customer. In that case, the waiting time will be 3, 7, & 17 respectively. Hence the average waiting time is (3 + 7 + 17) / 3 = 9.
 *
 * Help Tieu achieve the minimum average waiting time. For the sake of simplicity, just find the integer part of the minimum average waiting time.
 *
 * Input Format
 *
 * The first line contains an integer N, which is the number of customers.
 * In the next N lines, the ith line contains two space separated numbers Ti and Li. Ti is the time when ith customer order a pizza, and Li is the time required to cook that pizza.
 *
 * The  customer is not the customer arriving at the  arrival time.
 *
 * Output Format
 *
 * Display the integer part of the minimum average waiting time.
 * Constraints
 *
 * 1 ≤ N ≤ 105
 * 0 ≤ Ti ≤ 109
 * 1 ≤ Li ≤ 109
 * Note
 *
 * The waiting time is calculated as the difference between the time a customer orders pizza (the time at which they enter the shop) and the time she is served.
 *
 * Cook does not know about the future orders.
 *
 *
 * Sample Input #00
 *
 * 3
 * 0 3
 * 1 9
 * 2 6
 * Sample Output #00
 *
 * 9
 * Sample Input #01
 *
 * 3
 * 0 3
 * 1 9
 * 2 5
 * Sample Output #01
 *
 * 8
 * Explanation #01
 *
 * Let's call the person ordering at time = 0 as A, time = 1 as B and time = 2 as C. By delivering pizza for A, C and B we get the minimum average wait time to be
 *
 * (3 + 6 + 16)/3 = 25/3 = 8.33
 * the integer part is 8 and hence the answer.
 *
 */

public class MinimumAverageWaitingTime {

	static class CustomerOrder {

		int timeOfOrder;
		int timeToMakePizza;

		public CustomerOrder(int timeOfOrder, int timeToMakePizza) {
			this.timeOfOrder = timeOfOrder;
			this.timeToMakePizza = timeToMakePizza;
		}

		public int getTimeOfOrder() {
			return timeOfOrder;
		}

		public int getTimeToMakePizza() {
			return timeToMakePizza;
		}
	}

	static long minimumAverage(int[][] customers) {

		List<CustomerOrder> customerOrderList = new ArrayList<>();

		for(int i = 0; i < customers.length; ++i) {

			int timeOfOrder = customers[i][0];
			int timeToMakePizza = customers[i][1];
			customerOrderList.add(new CustomerOrder(timeOfOrder, timeToMakePizza));
		}

		if(customerOrderList.isEmpty()) return 0;

		customerOrderList.sort(Comparator.comparing(CustomerOrder::getTimeOfOrder));

		PriorityQueue<CustomerOrder> pq = new PriorityQueue<>(customerOrderList.size(), Comparator.comparing(CustomerOrder::getTimeToMakePizza));

		long sumWaitTime = 0;
		long curTime = 0;
		int processedCounter = 0;

		while(!pq.isEmpty() || processedCounter < customerOrderList.size()) {

			// queue orders received by time
			while(processedCounter < customerOrderList.size()
				&& customerOrderList.get(processedCounter).getTimeOfOrder() <= curTime) {
				pq.add(customerOrderList.get(processedCounter));
				++processedCounter;
			}

			// this is when we get we got nothing no new order by the time we completed the last order
			// reset the time and wait until (look in the input array) the next order comes our way
			if(pq.isEmpty()) {
				curTime = customerOrderList.get(processedCounter).getTimeOfOrder();
				continue;
			}

			CustomerOrder co = pq.remove();

			// move time - serve order
			curTime += co.getTimeToMakePizza();

			// add wait time
			sumWaitTime += curTime - co.getTimeOfOrder();
		}

		return sumWaitTime/customerOrderList.size();
	}


	public static void main(String[] args) {

		int[][] a = new int[][] {
			{961148050, 385599125},
			{951133776, 376367013},
			{283280121, 782916802},
			{317664929, 898415172},
			{980913391, 847912645}
		};
// answer = 1418670047

		int[][] c = new int[][] {
			{137857688, 413115088},
			{679783990, 171274023},
			{783725811, 742261681},
			{238387441, 531682046},
			{683427743, 559301752},
			{843391076, 398722857},
			{593760457, 2628387},
			{441381803, 788912528},
			{771854880, 916901718},
			{976015955, 978145894},
			{235492265, 264125858},
			{866638949, 551120745},
			{238176883, 201620672},
			{254029772, 950305054},
			{356294983, 203393748},
			{291672611, 722032663},
			{560013448, 126478507},
			{929678215, 321924654},
			{737812220, 884493567},
			{388266395, 252551113},
			{79292652, 229453232},
			{367753702, 242882326},
			{930211560, 461283594},
			{955372388, 594944846},
			{506995906, 872449795},
			{538015463, 457419763},
			{950540066, 820099707},
			{823860276, 896193555},
			{538832788, 47584891},
			{88242680, 700680580},
			{196842555, 623621497},
			{700528228, 610051112},
			{668066226, 170226832},
			{522278872, 914689320},
			{375621149, 336628938},
			{418416931, 270027322},
			{179882058, 480538711},
			{540671906, 215602397},
			{201411561, 930064341},
			{36616963, 35887002},
			{772894889, 944088968},
			{891134170, 633761602},
			{975099001, 434725536},
			{926070958, 326905396},
			{727328509, 867529847},
			{340789259, 890185621},
			{923620442, 986091986},
			{747688776, 107231383},
			{38070714, 495529501},
			{610348800, 235616181}
		};

		long x = MinimumAverageWaitingTime.minimumAverage(c);
		System.out.println(x);
		// answer = 8485548331

	}

}
