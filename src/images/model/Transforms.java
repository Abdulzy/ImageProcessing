package images.model;

/**
 * An interface called transforms.
 */
public interface Transforms {

  /**
   * Grey scales an image that is passed as an array, returns a grey scaled version of the array.
   *
   * @param arr the array that would be grey scaled
   * @return the grey scaled array
   * @throws IllegalArgumentException if the array is null
   */
  int[][][] greyScale(int[][][] arr);

  /**
   * Transforms an image into sepia tone which is passed as an array,
   * returns a sepia version of the array.
   *
   * @param arr the array that would be sepia
   * @return the sepia array
   * @throws IllegalArgumentException if the array is null
   */
  int[][][] sepia(int[][][] arr);

  /**
   * Reduces the colour density of an image that is passed as
   * an array, returns a sepia version of the array.
   *
   * @param factor the amount of color pellet the image is meant to have
   * @param arr    the array that would be have it's colour density reduced
   * @return the reduced colour density array
   * @throws IllegalArgumentException if the factor is a non positive
   * @throws IllegalArgumentException if the array is null
   */
  int[][][] reduceColourDensity(int[][][] arr, int factor);
}
