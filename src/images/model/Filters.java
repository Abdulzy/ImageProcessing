package images.model;

/**
 * An interface called filters.
 */
public interface Filters {

  /**
   * Blurs an image that is passed as an array, returns a blurred version of the array.
   *
   * @param intensity the amount of times you want the image blurred
   * @param arr       the array that would be blurred
   * @return the blurred array
   * @throws IllegalArgumentException if the intensity is a non positive
   * @throws IllegalArgumentException if the array is null
   */
  int[][][] blur(int[][][] arr, int intensity);

  /**
   * Sharpens an image that is passed as an array, returns a sharpened version of the array.
   *
   * @param intensity the amount of times you want the image sharpened
   * @param arr       the array that would be sharpened
   * @return the sharpened array
   * @throws IllegalArgumentException if the intensity is a non positive
   * @throws IllegalArgumentException if the array is null
   */
  int[][][] sharpen(int[][][] arr, int intensity);
}
