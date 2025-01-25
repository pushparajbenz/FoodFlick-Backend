package com.pn.food_cart_management.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pn.food_cart_management.entity.Cart;
import com.pn.food_cart_management.repository.CartRepository;
import com.pn.food_cart_management.service.CartServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartServiceImpl cartService;

    private Cart cart;
    
    private List<Cart> cartList;

    @BeforeEach
    void setUp() {
        cart = new Cart();
        cart.setCartId(1L);
        cart.setUserId(1L);
        cart.setInorder(false);

        cartList = Arrays.asList(cart);
    }

    @Test
    void testAddCart() {
    	when(cartRepository.save(cart)).thenReturn(cart); //mocks the save method of cartRepository to return the cart object when called
    	cartService.addCart(cart); //calls the method of service class
    	verify(cartRepository, times(1)).save(cart); //checks that the save method of cartRepository was called exactly once
    }

    @Test
    void testGetCart() {
      when(cartRepository.findByUserIdAndInorder(1L, false)).thenReturn(cartList);
      List<Cart> list = cartService.getCart(1L);
      verify(cartRepository, times(1)).findByUserIdAndInorder(1L, false);
      assertEquals(cartList, list);
    }

    @Test
    void testUpdateCart() {
    	when(cartRepository.save(cart)).thenReturn(cart);
        cartService.updateCart(cart);
        verify(cartRepository, times(1)).save(cart);
    }

    @Test
    void testDeleteCart() {
        cartService.deleteCart(1L);
        verify(cartRepository, times(1)).deleteById(1L);
    }

    @Test
    void testMoveFromCartToOrder() {
        when(cartRepository.findByUserIdAndInorder(1L, false)).thenReturn(cartList);
        cartService.moveFromCartToOrder(1L);
        verify(cartRepository, times(1)).findByUserIdAndInorder(1L, false);
        verify(cartRepository, times(1)).saveAll(cartList);
        assert(cartList.get(0).isInorder());
    }
}

