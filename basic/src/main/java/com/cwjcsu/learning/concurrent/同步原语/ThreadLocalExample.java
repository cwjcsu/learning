package com.cwjcsu.learning.concurrent.同步原语;

public class ThreadLocalExample {

    static class Context
    {
        public Context(String currentUser) {
            super();
            this.currentUser = currentUser;
        }

        String currentUser;
    }
    private static ThreadLocal<Context> context = new InheritableThreadLocal<Context>();
    public static Context currentContext()
    {
        return context.get();
    }
    public static void runAs(Runnable task,Context ctx)
    {
        Context lastContext = context.get();
        context.set(ctx);
        try
        {
            task.run();
        }
        finally
        {
            context.set(lastContext);
        }
    }
    public static String currentUser()
    {
        Context ctx = currentContext();
        if(ctx == null)
            return null;
        return ctx.currentUser;
    }
    public static void main(String[] args)
    {
        context.set(new Context("admin"));
        System.out.println("当前用户是：" + currentUser());
        Context userCtx = new Context("user");
        System.out.println("BEGIN RunAs " + userCtx.currentUser);
        runAs(new Runnable()
        {
            public void run()
            {
                System.out.println("当前用户是：" + currentUser());
                new Thread()
                {
                    public void run()
                    {
                        System.out.println("(UserThread)当前用户是：" + currentUser());
                    }
                }.start();
            }
        },new Context("user"));
        System.out.println("END RunAs");
        System.out.println("当前用户是：" + currentUser());
    }
}
