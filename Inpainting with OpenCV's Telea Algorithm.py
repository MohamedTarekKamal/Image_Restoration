# === Part 3: Apply OpenCV Telea inpainting ===
inpainted_telea = cv2.inpaint(image_cv, mask, inpaintRadius=30, flags=cv2.INPAINT_TELEA)
inpainted_telea_float = img_as_float(inpainted_telea)