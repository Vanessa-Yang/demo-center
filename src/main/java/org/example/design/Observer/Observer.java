package org.example.design.Observer;

/**
 * @Description Create contract for Observer
 * @Author VanessaYang
 * @Date: 2023/4/10 0010 11:25
 **/
public interface Observer {

    // method to update the observer, used by subject
    void update();

    // attach with subject to observe
    void setSubject(Subject sub);

}
