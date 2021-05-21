package com.example.bookreservationserver.borrow.controller;

import com.example.bookreservationserver.borrow.dto.BorrowRequest;
import com.example.bookreservationserver.borrow.dto.BorrowResponse;
import com.example.bookreservationserver.borrow.service.BorrowService;
import com.example.bookreservationserver.borrow.service.ReturnService;
import com.example.bookreservationserver.borrow.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BorrowApi {
    private SearchService searchService;
    private ReturnService returnService;
    private BorrowService borrowService;

    @PostMapping(value = "/api/borrow/create", produces = "application/json; charset=utf8")
    public BorrowResponse borrow(@RequestBody @Valid BorrowRequest borrowRequest){
        return borrowService.borrowBook(borrowRequest);
    }

    @PostMapping("/api/borrow/{borrowId}/return")
    public String returnBook(@PathVariable("borrowId") Long bookId){
        returnService.returnBook(bookId);
        return "반납이 완료되었습니다.";
    }
    
    @GetMapping("/api/borrow/all/{userId}")
    public List<BorrowResponse> searchMyBorrow(@PathVariable("userId") Long userId){
        return searchService.getMyReservations(userId);
    }

    @GetMapping("/api/borrow/borrowing/{userId}")
    public List<BorrowResponse> searchMyBorrowing(@PathVariable("userId") Long userId){
        return searchService.getMyBorrowingReservations(userId);
    }

    @Autowired
    public BorrowApi(SearchService searchService, ReturnService returnService, BorrowService borrowService) {
        this.searchService = searchService;
        this.returnService = returnService;
        this.borrowService = borrowService;
    }
}
