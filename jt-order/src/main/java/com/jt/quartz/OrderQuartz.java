package com.jt.quartz;

import java.util.Calendar;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.mapper.OrderMapper;
import com.jt.pojo.Order;



//准备订单定时任务
@Component
public class OrderQuartz extends QuartzJobBean{
	//定时任务执行时的具体操作
	@Autowired
	private OrderMapper orderMapper;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		//获取格林威治时间
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.MINUTE, -15); //进行时间运算
				Date timeOut = calendar.getTime();  //获取超时时间
				Order order = new Order();
				order.setStatus(6).setUpdated(new Date());
				UpdateWrapper<Order> updateWrapper = 
						new UpdateWrapper<>();
				updateWrapper.eq("status", 1)
							 .lt("created", timeOut);
				orderMapper.update(order, updateWrapper);
				System.out.println("定时任务执行成功!!!!!京淘项目全部完成!!!!!!");
			}	
		
}
	
	/*@Autowired
	private OrderService orderService;
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		
		orderService.updateOrderStatus();
		System.out.println("定时任务执行");
	}
}*/
