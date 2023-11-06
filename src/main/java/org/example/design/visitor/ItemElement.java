package org.example.design.visitor;

/**
 * @Description
 * @Author VanessaYang
 * @Date: 2023/4/11 0011 13:53
 **/
public interface ItemElement {

    int accept(ShoppingCartVisitor visitor);
}
