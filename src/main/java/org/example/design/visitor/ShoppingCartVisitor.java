package org.example.design.visitor;

/**
 * @Description
 * @Author VanessaYang
 * @Date: 2023/4/11 0011 13:59
 **/
public interface ShoppingCartVisitor {

    int visit(Book book);
    int visit(Fruit fruit);

}
