
# === Import Required Libraries ===
import numpy as np
import matplotlib.pyplot as plt
from skimage import io, img_as_float, img_as_ubyte, exposure
from skimage.restoration import inpaint
import cv2
import os

# === Define Image Path ===
base_path = r'C:\Users\miado\Desktop'
image_base_name = '9bd18077df4cbeef31ddb4b9f6977629'
input_image_path = os.path.join(base_path, image_base_name + '.jpg')

# === Load and Convert the Image to Grayscale Float Format ===
original_image = img_as_float(io.imread(input_image_path, as_gray=True))
image_cv = img_as_ubyte(original_image)

# === Crack Detection Using Canny and Mask Creation by Dilation ===
edges = cv2.Canny(image_cv, 30, 150)
kernel = np.ones((3, 3), np.uint8)
mask = cv2.dilate(edges, kernel, iterations=2)

# === Inpainting Using OpenCV's Telea Method ===
inpainted_telea = cv2.inpaint(image_cv, mask, inpaintRadius=30, flags=cv2.INPAINT_TELEA)
inpainted_telea_float = img_as_float(inpainted_telea)

# === Inpainting Using Biharmonic Method from scikit-image ===
image_rgb = cv2.cvtColor(image_cv, cv2.COLOR_GRAY2RGB)
mask_bool = mask.astype(bool)
inpainted_biharmonic = inpaint.inpaint_biharmonic(image_rgb, mask_bool, channel_axis=-1)
inpainted_biharmonic_gray = img_as_float(cv2.cvtColor(img_as_ubyte(inpainted_biharmonic), cv2.COLOR_RGB2GRAY))

# === Combine the Results from Both Inpainting Methods ===
restored_combined = 0.6 * inpainted_telea_float + 0.4 * inpainted_biharmonic_gray

# === Contrast Enhancement ===
# Method 1: Local Histogram Equalization (CLAHE)
restored_eq_adapthist = exposure.equalize_adapthist(restored_combined, clip_limit=0.0025)

# Method 2: Global Intensity Rescaling
p2, p98 = np.percentile(restored_eq_adapthist, (10, 99.3))
restored_contrast = exposure.rescale_intensity(restored_eq_adapthist, in_range=(p2, p98))

# === Visualization of Original and Restored Images ===
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

# === Save Final Restored Images ===
io.imsave(os.path.join(base_path, image_base_name + '_restored_combined.png'), img_as_ubyte(restored_combined))
io.imsave(os.path.join(base_path, image_base_name + '_restored_contrast.png'), img_as_ubyte(restored_contrast))
