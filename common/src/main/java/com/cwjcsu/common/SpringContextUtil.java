package com.cwjcsu.common;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.ClassUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


//@Component(value = "springContextUtil")
public class SpringContextUtil implements ApplicationContextAware {
	private static final Logger log = _.COMMON;
	private static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		context = ctx;
		inContainer=true;
		bpp.setBeanFactory(context.getAutowireCapableBeanFactory());
	}

	public static ApplicationContext getApplicationContext() {
		return context;
	}

	public static <T> T getBean(String name) throws BeansException {
		ApplicationContext ctx = context;
		T t = null;
		while (ctx != null && (t = (T) ctx.getBean(name)) == null)
			ctx = ctx.getParent();
		return (T) t;
	}

	public static <T> T getBean(Class<T> type) throws BeansException {
		ApplicationContext ctx = context;
		Map map = Collections.EMPTY_MAP;
		while (ctx != null && (map = ctx.getBeansOfType(type)) != null
				&& map.size() == 0) {
			ctx = ctx.getParent();
		}
		if (map == null || map.size() == 0)
			throw new RuntimeException("No bean of type  " + type
					+ " is defined.");
		if (map.size() > 1) {
			throw new RuntimeException("Got more then 1 bean of type " + type
					+ ",they are " + map);
		}
		return (T) map.values().iterator().next();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> T getBean(ApplicationContext context, Class<T> type)
			throws BeansException {
		Map map = context.getBeansOfType(type);
		if (map.size() > 1) {
			throw new NoSuchBeanDefinitionException(
					"Got more then 1 bean of type " + type + ",they are " + map);
		}
		if (map.size() == 0)
			throw new NoSuchBeanDefinitionException("No bean of type  " + type
					+ " is defined.");
		return (T) map.values().iterator().next();
	}

	public static WebApplicationContext getWebApplicationContext(
			ServletContext sc) {
		return WebApplicationContextUtils.getWebApplicationContext(sc);
	}

	public static void processInjectionBasedOnCurrentContext(
			List<? extends Object> targets) {
		if (targets != null) {
			for (Object target : targets) {
				processInjectionBasedOnCurrentContext(target);
			}
		}
	}

	public static void autowire(List targets) {
		if (targets != null) {
			for (Object target : targets) {
				processInjectionBasedOnCurrentContext(target);
			}
		}
	}

	public static void autowire(Object... targets) {
		if (targets != null) {
			for (Object target : targets) {
				processInjectionBasedOnCurrentContext(target);
			}
		}
	}

	private static AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();

	public static void processInjectionBasedOnCurrentContext(Object target) {
		if (target != null) {
			if (context == null || bpp == null) {
				throw new IllegalStateException(
						"Current WebApplicationContext is not available for processing of "
								+ ClassUtils.getShortName(target.getClass())
								+ ": "
								+ "Make sure this class gets constructed in a Spring web application. Proceeding without injection.");
			}
			bpp.processInjection(target);
		}
	}

	public static void exit(int exitCode, String fatalError) {
		if (fatalError != null) {
			System.setProperty("ha.fatal_error", fatalError);
		}
		new Thread(new Runnable() {
			public void run() {
				System.exit(1);
			}
		}, "EXIT").start();
	}
	
	private static boolean inContainer;

	/**
	 * 当前JVM是否启动了Spring容器
	 * @return
	 */
	public static boolean isInContainer() {
		return inContainer;
	}


	/**
	 * 
	 * @return Spring正在停止或者已经停止，not running
	 */
	// public static boolean isHaStopping() {
	// try {
	// HaContext ctx = SpringContextUtil.getBean(HaContext.class);
	// if (ctx != null && ctx.isCmmStopped()) {
	// return true;
	// }
	// } catch (Exception e) {
	// log.warn("" + e.getMessage(), e);
	// }
	// return false;
	// }

}