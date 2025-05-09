
import matplotlib.pyplot as plt
from skimage import io, img_as_ubyte
import os

# === Display and Save Results ===

# Display the original, restored (before contrast), and restored (after contrast) images side by side
plt.figure(figsize=(15, 5))
plt.subplot(1, 3, 1)
plt.imshow(original_image, cmap='gray')
plt.title("Original Image")

plt.subplot(1, 3, 2)
plt.imshow(restored_combined, cmap='gray')
plt.title("Restored (Before Contrast)")

plt.subplot(1, 3, 3)
plt.imshow(restored_contrast, cmap='gray')
plt.title("Restored (After Contrast)")

plt.tight_layout()
plt.savefig(os.path.join(base_path, image_base_name + '_final_contrast_result.png'), dpi=300)
plt.show()

# Save the intermediate and final results to disk
io.imsave(os.path.join(base_path, image_base_name + '_restored_combined.png'), img_as_ubyte(restored_combined))
io.imsave(os.path.join(base_path, image_base_name + '_restored_contrast.png'), img_as_ubyte(restored_contrast))
