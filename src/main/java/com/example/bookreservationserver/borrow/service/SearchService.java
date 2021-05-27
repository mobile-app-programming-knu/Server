package com.example.bookreservationserver.borrow.service;

import com.example.bookreservationserver.book.domain.aggregate.Book;
import com.example.bookreservationserver.book.domain.repository.BookEntityRepository;
import com.example.bookreservationserver.borrow.domain.aggregate.Borrow;
import com.example.bookreservationserver.borrow.domain.aggregate.BorrowState;
import com.example.bookreservationserver.borrow.domain.repository.BorrowEntityRepository;
import com.example.bookreservationserver.borrow.dto.BorrowResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SearchService {
    private BorrowEntityRepository borrowEntityRepository;
    private BookEntityRepository bookEntityRepository;

    public List<BorrowResponse> getMyBorrowingReservations(Long userId){
        List<Borrow> borrows = borrowEntityRepository.findBorrowsByBorrower_UserIdAndState(userId, BorrowState.BORROWING);
        return borrows.stream().map(b -> {
            Book book = bookEntityRepository.findById(b.getBookId()).get();
            return new BorrowResponse(b,book);
        }).collect(Collectors.toList());
    }

    public List<BorrowResponse> getMyReservations(Long userId){
        List<Borrow> borrows = borrowEntityRepository.findBorrowsByBorrower_UserId(userId);
        return borrows.stream().map(b -> {
            Book book = bookEntityRepository.findById(b.getBookId()).get();
            return new BorrowResponse(b, book);
        }).collect(Collectors.toList());
    }

    // all expired reservations 만들기


    @Autowired
    public SearchService(BorrowEntityRepository borrowEntityRepository, BookEntityRepository bookEntityRepository) {
        this.borrowEntityRepository = borrowEntityRepository;
        this.bookEntityRepository = bookEntityRepository;
    }
}
