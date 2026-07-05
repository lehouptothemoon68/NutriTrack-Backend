package htw.webtech.NutriTrack;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NutriTrackApplicationTests {

	@Mock
	private MealRepository repository;

	@InjectMocks
	private MealService service;

	@Test
	void test1_getAllMeals_gibtListeZurueck() {
		MealEntry meal = new MealEntry("Pizza", new Macronutrient(20, 50, 15));
		when(repository.findAll()).thenReturn(List.of(meal));

		List<MealEntry> result = service.getAllMeals();

		assertEquals(1, result.size());
		assertEquals("Pizza", result.get(0).getName());
	}

	@Test
	void test2_saveMeal_speichertMahlzeit() {
		MealEntry meal = new MealEntry("Burger", new Macronutrient(25, 40, 20));
		when(repository.save(meal)).thenReturn(meal);

		MealEntry result = service.save(meal);

		assertEquals("Burger", result.getName());
	}

	@Test
	void test3_deleteMeal_loeschtMahlzeit() {
		doNothing().when(repository).deleteById(1L);

		service.delete(1L);

		verify(repository, times(1)).deleteById(1L);
	}

	@Test
	void test4_updateMeal_aktualisiertName() {
		MealEntry existing = new MealEntry("Alt", new Macronutrient(10, 10, 10));
		existing.setId(1L);
		MealEntry updated = new MealEntry("Neu", new Macronutrient(20, 20, 20));

		when(repository.findById(1L)).thenReturn(Optional.of(existing));
		when(repository.save(existing)).thenReturn(existing);

		MealEntry result = service.update(1L, updated);

		assertEquals("Neu", result.getName());
	}

	@Test
	void test5_updateMeal_aktualisiertMakros() {
		MealEntry existing = new MealEntry("Pizza", new Macronutrient(10, 10, 10));
		existing.setId(1L);
		MealEntry updated = new MealEntry("Pizza", new Macronutrient(20, 50, 15));

		when(repository.findById(1L)).thenReturn(Optional.of(existing));
		when(repository.save(existing)).thenReturn(existing);

		MealEntry result = service.update(1L, updated);

		assertEquals(50, result.getMacro().getCountCarbs());
	}

	@Test
	void test6_saveMeal_mitFavorit() {
		MealEntry meal = new MealEntry("Salat", new Macronutrient(5, 10, 8));
		meal.setFavorite(true);
		when(repository.save(meal)).thenReturn(meal);

		MealEntry result = service.save(meal);

		assertTrue(result.isFavorite());
	}

	@Test
	void test7_getAllMeals_leereListeWennKeineEintraege() {
		when(repository.findAll()).thenReturn(List.of());

		List<MealEntry> result = service.getAllMeals();

		assertTrue(result.isEmpty());
	}

	@Test
	void test8_updateMeal_setzFavoritStatus() {
		MealEntry existing = new MealEntry("Pasta", new Macronutrient(10, 60, 12));
		existing.setId(1L);
		existing.setFavorite(false);
		MealEntry updated = new MealEntry("Pasta", new Macronutrient(10, 60, 12));
		updated.setFavorite(true);

		when(repository.findById(1L)).thenReturn(Optional.of(existing));
		when(repository.save(existing)).thenReturn(existing);

		MealEntry result = service.update(1L, updated);

		assertTrue(result.isFavorite());
	}
}