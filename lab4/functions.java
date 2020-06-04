package lab4;

import java.util.concurrent.Exchanger;

//this class gets results
class result {
    private final double data;

    result(double data) {
        this.data = data;
    }

    public synchronized double getData() {
        return this.data;
    }
}

//calculation of f function
class funcF implements Runnable {
    private final Exchanger<result> exchanger;
    private result result;

    funcF(Exchanger<result> exchanger) {
        this.exchanger = exchanger;
    }

    private result f (result x) {
        double res, temp;
        temp = x.getData();
        res = (temp*temp*temp)%23;
        return new result(res);
    }

    @Override
    public void run() {
        try {
            while (result == null) {
                result = this.exchanger.exchange(null);
            }

            result = this.exchanger.exchange(this.f(result));

        } catch (InterruptedException ignored) {
        }
    }
}


//calculation of g function
class funcG implements Runnable {
    private final Exchanger<result> exchanger;
    private result result;

    funcG(Exchanger<result> exchanger) {
        this.exchanger = exchanger;
    }

    private result g (result x){
        double res, temp;
        temp = x.getData();
        res = (temp*temp*596.33)%423;
        return new result(res);
    }

    @Override
    public void run() {
        try {
            while (result == null) {
                result = this.exchanger.exchange(null);
            }

            result = this.exchanger.exchange(this.g(result));

        } catch (InterruptedException ignored) {
        }
    }
}
