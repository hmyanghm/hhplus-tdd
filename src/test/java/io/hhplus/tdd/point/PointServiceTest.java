package io.hhplus.tdd.point;

import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.database.UserPointTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("Mock Object When Then Test")
@ExtendWith(MockitoExtension.class)
class PointServiceTest {

    @Mock
    private UserPointTable userPointTable;

    @Mock
    private PointHistoryTable pointHistoryTable;

    // Mockito 초기화
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void 초기_조회시_user가_0포인트를_가진다() {

        // Given
        long id = 1;
        when(userPointTable.selectById(id)).thenReturn(UserPoint.empty(id));

        // When
        long point = userPointTable.selectById(id).point();

        // Then
        assertEquals(point, 0);
    }

    @Test
    public void user포인트_100_충전하면_100포인트() {

        // Given
        long id = 1;
        long amount = 100;
        when(userPointTable.insertOrUpdate(id, amount)).thenReturn(new UserPoint(id, amount, System.currentTimeMillis()));

        // When
        long balance = userPointTable.insertOrUpdate(id, amount).point();

        // Then
        assertEquals(balance, 100);
    }

}